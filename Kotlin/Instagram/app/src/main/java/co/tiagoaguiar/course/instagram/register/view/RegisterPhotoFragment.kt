package co.tiagoaguiar.course.instagram.register.view

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.content.res.ColorStateList
import android.content.res.Configuration
import android.graphics.Color
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import co.tiagoaguiar.course.instagram.R
import co.tiagoaguiar.course.instagram.commom.base.DependencyInjector
import co.tiagoaguiar.course.instagram.commom.view.CropperImageFragment.Companion.CROP_KEY
import co.tiagoaguiar.course.instagram.commom.view.CropperImageFragment.Companion.KEY_URI
import co.tiagoaguiar.course.instagram.commom.view.CustomDialog
import co.tiagoaguiar.course.instagram.databinding.FragmentRegisterPhotoBinding
import co.tiagoaguiar.course.instagram.register.RegisterPhoto
import co.tiagoaguiar.course.instagram.register.presentation.RegisterPhotoPresenter

@Suppress("DEPRECATION")
class RegisterPhotoFragment : Fragment(R.layout.fragment_register_photo), RegisterPhoto.View {

    private var binding: FragmentRegisterPhotoBinding? = null
    private var fragmentAttachListener: FragmentAttachListener? = null
    override lateinit var presenter: RegisterPhoto.Presenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener(CROP_KEY) { _, bundle ->
            val uri = bundle.getParcelable<Uri>(KEY_URI)
            onCropImageResult(uri)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRegisterPhotoBinding.bind(view)
        val repository = DependencyInjector.registerEmailRepository()
        presenter = RegisterPhotoPresenter(this, repository)

        binding?.let {
            with(it) {
                when(resources.configuration?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK)) {
                    Configuration.UI_MODE_NIGHT_YES -> {
                        imageViewRegisterPhoto.imageTintList = ColorStateList.valueOf(Color.WHITE)
                    }
                }
                buttonRegisterSkipPhoto.setOnClickListener {
                    fragmentAttachListener?.goToMainScreen()
                }
                buttonRegisterAddPhoto.isEnabled = true
                buttonRegisterAddPhoto.setOnClickListener {
                    openDialog()
                }
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentAttachListener) {
            fragmentAttachListener = context
        }
    }

    override fun onDestroy() {
        binding = null
        fragmentAttachListener = null
        presenter.onDestroy()
        super.onDestroy()
    }

    private fun openDialog() {
        val customDialog = CustomDialog(requireContext())
        customDialog.addButton(R.string.photo, R.string.gallery) {
            when (it.id) {
                R.string.photo -> {
                    Log.d(TAG, "Photo")
                    when (allPermissionsGranted()) {
                        true -> fragmentAttachListener?.goToCameraScreen()
                        false -> getPermission.launch(REQUIRED_PERMISSION)
                    }
                }

                R.string.gallery -> {
                    Log.d(TAG, "Gallery")
                    fragmentAttachListener?.goToGalleryScreen()
                }

                else -> {
                    throw IllegalStateException("Unexpected value: ${it.id}")
                }
            }
        }
        customDialog.show()
    }

    private val getPermission = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { _ ->
        if (allPermissionsGranted()) {
            fragmentAttachListener?.goToCameraScreen()
        } else {
            Toast.makeText(requireContext(), R.string.permission_camera_denied, Toast.LENGTH_SHORT).show()
        }
    }


    private fun allPermissionsGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            REQUIRED_PERMISSION[0]) == PackageManager.PERMISSION_GRANTED
    }

    private fun onCropImageResult(uri: Uri?) {
        uri?.let {
            val bitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                val source = ImageDecoder.createSource(requireContext().contentResolver, it)
                ImageDecoder.decodeBitmap(source)
            } else {
                MediaStore.Images.Media.getBitmap(requireContext().contentResolver, it)
            }
            binding?.imageViewRegisterPhoto?.setImageBitmap(bitmap)
            presenter.updateUser(uri)
        }
    }

    override fun showProgress(enabled: Boolean) {
       binding?.buttonRegisterAddPhoto?.showProgress(enabled)
    }

    override fun onUpdateFailure(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
    }

    override fun onUpdateSuccess() {
        fragmentAttachListener?.goToMainScreen()
    }

    companion object {
        private const val TAG = "RegisterPhotoFragment"
        private val REQUIRED_PERMISSION = arrayOf(Manifest.permission.CAMERA)
    }

}