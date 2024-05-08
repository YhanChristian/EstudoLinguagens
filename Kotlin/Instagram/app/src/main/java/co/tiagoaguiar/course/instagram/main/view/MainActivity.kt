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
import co.tiagoaguiar.course.instagram.post.view.AddFragment
import co.tiagoaguiar.course.instagram.commom.extensions.replaceFragment
import co.tiagoaguiar.course.instagram.databinding.ActivityMainBinding
import co.tiagoaguiar.course.instagram.home.view.HomeFragment
import co.tiagoaguiar.course.instagram.profile.view.ProfileFragment
import co.tiagoaguiar.course.instagram.search.view.SearchFragment
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(),
    BottomNavigationView.OnNavigationItemSelectedListener,
    AddFragment.AddListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var homeFragment: HomeFragment
    private lateinit var profileFragment: ProfileFragment
    private lateinit var searchFragment: Fragment
    private lateinit var addFragment: Fragment
    private var currentFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.setSystemBarsAppearance(
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
            )
            window.statusBarColor = ContextCompat.getColor(this, R.color.gray)
        }
        setSupportActionBar(binding.toolbarMain)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_insta_camera)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""

        homeFragment = HomeFragment()
        profileFragment = ProfileFragment()
        searchFragment = SearchFragment()
        addFragment = AddFragment()

        binding.bottomNavMain.setOnNavigationItemSelectedListener(this)
        binding.bottomNavMain.selectedItemId = R.id.menu_bottom_home
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var scrollToolbarEnabled = false
        when (item.itemId) {
            R.id.menu_bottom_home -> {
                if (currentFragment == homeFragment) return false
                currentFragment = homeFragment
            }

            R.id.menu_bottom_profile -> {
                if (currentFragment == profileFragment) return false
                currentFragment = profileFragment
                scrollToolbarEnabled = true
            }

            R.id.menu_bottom_search -> {
                if (currentFragment == searchFragment) return false
                currentFragment = searchFragment
            }

            R.id.menu_bottom_add_photo -> {
                if (currentFragment == addFragment) return false
                currentFragment = addFragment
            }
        }
        setScrollToolbarEnabled(scrollToolbarEnabled)
        currentFragment?.let {
            replaceFragment(R.id.fragment_main, it)
        }
        return true
    }

    /*Habilita e desabilita a rolagem da toolBar*/
    private fun setScrollToolbarEnabled(enabled: Boolean) {
        val params = binding.toolbarMain.layoutParams as AppBarLayout.LayoutParams
        val coordinatorParams = binding.appbarMain.layoutParams as CoordinatorLayout.LayoutParams
        if (enabled) {
            params.scrollFlags =
                AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS
            coordinatorParams.behavior = AppBarLayout.Behavior()
        } else {
            params.scrollFlags = 0
            coordinatorParams.behavior = null
        }
        binding.appbarMain.layoutParams = coordinatorParams
    }

    override fun onPostCreated() {
        homeFragment.presenter.clear()
        if(supportFragmentManager.findFragmentByTag(profileFragment.javaClass.simpleName) != null) {
            profileFragment.presenter.clear()
        }
        binding.bottomNavMain.selectedItemId = R.id.menu_bottom_home
    }
}