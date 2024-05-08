package co.tiagoaguiar.course.instagram.post.view


import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import co.tiagoaguiar.course.instagram.R
import co.tiagoaguiar.course.instagram.add.view.AddActivity
import co.tiagoaguiar.course.instagram.add.view.AddActivity.Companion.KEY_PHOTO_URI
import co.tiagoaguiar.course.instagram.post.view.CameraFragment.Companion.KEY_TAKE_PHOTO
import co.tiagoaguiar.course.instagram.post.view.CameraFragment.Companion.KEY_URI
import co.tiagoaguiar.course.instagram.databinding.FragmentAddBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class AddFragment : Fragment(R.layout.fragment_add) {

    var binding : FragmentAddBinding? = null
    private var addListener: AddListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener(KEY_TAKE_PHOTO) { _, bundle ->
            val uri = bundle.getParcelable<Uri>(KEY_URI)
            uri?.let {
                val intent = Intent(requireContext(), AddActivity::class.java)
                intent.putExtra(KEY_PHOTO_URI, uri)
                addActivityResult.launch(intent)
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is AddListener) {
            addListener = context
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddBinding.bind(view)
        if(savedInstanceState == null) {
            setupViews()
        }
    }
    private fun setupViews() {
        val tabLayout = binding?.tabAdd
        val viewPager = binding?.viewPagerAdd
        val adapter = AddViewPagerAdapter(requireActivity())
        viewPager?.adapter = adapter
        if (tabLayout != null && viewPager != null) {
            tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    if(tab?.text == getString(adapter.tabs[0])) {
                        startCamera()
                    }
                }
                override fun onTabUnselected(tab: TabLayout.Tab?) {
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                }
            })
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = getString(adapter.tabs[position])
            }.attach()
        }
        when (allPermissionsGranted()) {
            true -> startCamera()
            false -> getPermission.launch(REQUIRED_PERMISSION)
        }
    }

    private val addActivityResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == Activity.RESULT_OK) {
            addListener?.onPostCreated()
        }
    }

    private val getPermission = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { granted ->
        if (allPermissionsGranted()) {
            startCamera()
        } else {
            Toast.makeText(requireContext(), R.string.permission_camera_denied, Toast.LENGTH_SHORT).show()
        }
    }
    private fun startCamera() {
        Log.d(TAG, "startCamera success")
        setFragmentResult("cameraKey", bundleOf("startCamera" to true))
    }

    private fun allPermissionsGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            REQUIRED_PERMISSION[0]
        ) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
            requireContext(),
            REQUIRED_PERMISSION[1]
        ) == PackageManager.PERMISSION_GRANTED
    }

    interface AddListener {
        fun onPostCreated()
    }


    companion object {
        private val REQUIRED_PERMISSION = arrayOf(Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE)
        private const val TAG = "AddFragment"
    }
}