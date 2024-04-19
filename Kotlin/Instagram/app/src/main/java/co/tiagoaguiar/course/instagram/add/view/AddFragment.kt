package co.tiagoaguiar.course.instagram.add.view


import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import android.widget.TableLayout
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import co.tiagoaguiar.course.instagram.R
import co.tiagoaguiar.course.instagram.add.Add
import co.tiagoaguiar.course.instagram.commom.base.BaseFragment
import co.tiagoaguiar.course.instagram.databinding.FragmentAddBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class AddFragment : BaseFragment<FragmentAddBinding, Add.Presenter>(
    R.layout.fragment_add,
    FragmentAddBinding::bind
), Add.View {

    override lateinit var presenter: Add.Presenter
    override fun setupViews() {
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

    private val getPermission = registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
        if (granted) {
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
            REQUIRED_PERMISSION
        ) == PackageManager.PERMISSION_GRANTED
    }

    override fun setupPresenter() {

    }

    companion object {
        private const val REQUIRED_PERMISSION =  Manifest.permission.CAMERA
        private const val TAG = "AddFragment"
    }
}