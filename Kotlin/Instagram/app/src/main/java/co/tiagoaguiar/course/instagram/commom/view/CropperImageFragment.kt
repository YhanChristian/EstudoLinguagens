package co.tiagoaguiar.course.instagram.commom.view

import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import co.tiagoaguiar.course.instagram.R
import co.tiagoaguiar.course.instagram.databinding.FragmentImageCropperBinding
import java.io.File

class CropperImageFragment : Fragment(R.layout.fragment_image_cropper) {
    companion object {
        private const val TAG = "CropperImageFragment"
        const val KEY_URI = "key_uri"
        const val CROP_KEY = "crop_key"
    }

    private var binding : FragmentImageCropperBinding? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentImageCropperBinding.bind(view)
        val uri = arguments?.getParcelable<Uri>(KEY_URI)

        binding?.let {
            with(it) {
                containerImageCropper.setAspectRatio(1, 1)
                containerImageCropper.setFixedAspectRatio(true)
                containerImageCropper.setImageUriAsync(uri)

                buttonCropperCancel.setOnClickListener {
                    parentFragmentManager.popBackStack()
                }

                containerImageCropper.setOnCropImageCompleteListener() { _, result ->
                    Log.d(TAG, "onCropImageComplete: ${result.uri}")
                    setFragmentResult(CROP_KEY, bundleOf(KEY_URI to result.uri))
                    parentFragmentManager.popBackStack()
                }

                buttonCropperSave.setOnClickListener {
                    val dir = context?.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
                    dir?.let {
                        val uriToSaved = Uri.fromFile(File(dir.path, System.currentTimeMillis().toString() + ".jpg"))
                        containerImageCropper.saveCroppedImageAsync(uriToSaved)
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}