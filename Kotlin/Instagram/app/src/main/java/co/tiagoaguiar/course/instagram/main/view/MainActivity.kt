package co.tiagoaguiar.course.instagram.main.view

import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.WindowInsetsController
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import co.tiagoaguiar.course.instagram.R
import co.tiagoaguiar.course.instagram.camera.view.CameraFragment
import co.tiagoaguiar.course.instagram.databinding.ActivityMainBinding
import co.tiagoaguiar.course.instagram.home.view.HomeFragment
import co.tiagoaguiar.course.instagram.profile.view.ProfileFragment
import co.tiagoaguiar.course.instagram.search.view.SearchFragment
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding : ActivityMainBinding
    private lateinit var homeFragment : Fragment
    private lateinit var profileFragment : Fragment
    private lateinit var searchFragment : Fragment
    private lateinit var cameraFragment : Fragment
    private lateinit var currentFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R)
        {
            window.insetsController?.setSystemBarsAppearance(
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS)
            window.statusBarColor = ContextCompat.getColor(this, R.color.gray)
        }
        setSupportActionBar(binding.toolbarMain)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_insta_camera)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""

        homeFragment = HomeFragment()
        profileFragment = ProfileFragment()
        searchFragment = SearchFragment()
        cameraFragment = CameraFragment()

        currentFragment = homeFragment

        /*Carrega os fragments na activity*/
        supportFragmentManager.beginTransaction().apply {
            add(R.id.fragment_main, homeFragment, "0")
            add(R.id.fragment_main, profileFragment, "3").hide(profileFragment)
            add(R.id.fragment_main, searchFragment, "1").hide(searchFragment)
            add(R.id.fragment_main, cameraFragment, "2").hide(cameraFragment)
            commit()
        }

        binding.bottomNavMain.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var scrollToolbarEnabled = false
        when(item.itemId) {
            R.id.menu_bottom_home -> {
                if(currentFragment == homeFragment) return false
                supportFragmentManager.beginTransaction().apply {
                    hide(currentFragment)
                    show(homeFragment)
                    commit()
                }
                currentFragment = homeFragment
            }
            R.id.menu_bottom_profile -> {
                if(currentFragment == profileFragment) return false
                supportFragmentManager.beginTransaction().apply {
                    hide(currentFragment)
                    show(profileFragment)
                    commit()
                }
                currentFragment = profileFragment
                scrollToolbarEnabled = true
            }

            R.id.menu_bottom_search -> {
                if(currentFragment == searchFragment) return false
                supportFragmentManager.beginTransaction().apply {
                    hide(currentFragment)
                    show(searchFragment)
                    commit()
                }
                currentFragment = searchFragment
            }
            R.id.menu_bottom_add_photo -> {
                if(currentFragment == cameraFragment) return false
                supportFragmentManager.beginTransaction().apply {
                    hide(currentFragment)
                    show(cameraFragment)
                    commit()
                }
                currentFragment = cameraFragment
            }
        }
        setScrollToolbarEnabled(scrollToolbarEnabled)
        return true
    }

    /*Habilita e desabilita a rolagem da toolBar*/
    private fun setScrollToolbarEnabled(enabled: Boolean) {
        val params = binding.toolbarMain.layoutParams as AppBarLayout.LayoutParams
        val coordinatorParams = binding.appbarMain.layoutParams as CoordinatorLayout.LayoutParams
        if(enabled) {
            params.scrollFlags = AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS
            coordinatorParams.behavior = AppBarLayout.Behavior()
        } else {
            params.scrollFlags = 0
            coordinatorParams.behavior = null
        }
        binding.appbarMain.layoutParams = coordinatorParams
    }
}