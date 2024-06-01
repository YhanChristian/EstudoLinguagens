package co.tiagoaguiar.course.instagram.profile.view

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import co.tiagoaguiar.course.instagram.R
import co.tiagoaguiar.course.instagram.commom.base.BaseFragment
import co.tiagoaguiar.course.instagram.commom.base.DependencyInjector
import co.tiagoaguiar.course.instagram.commom.model.Post
import co.tiagoaguiar.course.instagram.commom.model.UserAuth
import co.tiagoaguiar.course.instagram.databinding.FragmentProfileBinding
import co.tiagoaguiar.course.instagram.profile.Profile
import co.tiagoaguiar.course.instagram.profile.presentation.ProfilePresenter
import com.google.android.material.bottomnavigation.BottomNavigationView

class ProfileFragment : BaseFragment<FragmentProfileBinding, Profile.Presenter>
    (R.layout.fragment_profile, FragmentProfileBinding::bind),
    Profile.View, BottomNavigationView.OnNavigationItemSelectedListener {

    override lateinit var presenter: Profile.Presenter
    private val adapter = PostAdapter()
    private var uuid: String? = null

    override fun setupViews() {
        uuid = arguments?.getString(KEY_USER_UUID)
        binding?.rvProfile?.layoutManager = GridLayoutManager(requireContext(), 3)
        binding?.rvProfile?.adapter = adapter
        binding?.navProfileTabs?.setOnNavigationItemSelectedListener(this)
        presenter.fetchUserProfile(uuid)
    }

    override fun setupPresenter() {
       val repository = DependencyInjector.profileRepository()
        presenter = ProfilePresenter(this, repository)
    }

    override fun getMenu(): Int {
        return R.menu.menu_profile
    }

    override fun showProgress(enabled: Boolean) {
        binding?.progressProfile?.visibility = if (enabled) View.VISIBLE else View.GONE
    }

    override fun displayUserProfile(userAuth: Pair<UserAuth, Boolean?>) {
        val (user, following) = userAuth
        binding?.textProfilePostsCount?.text = user.postCount.toString()
        binding?.textProfileFollowingCount?.text = user.followingCount.toString()
        binding?.textProfileFollowersCount?.text = user.followerCount.toString()
        binding?.textProfileUsername?.text = user.name
        binding?.textProfileUsernameBio?.text = "TODO"
        binding?.imageProfileIcon?.setImageURI(user.photoUri)

        binding?.buttonEditProfile?.text = when (following) {
            null -> getString(R.string.edit_profile)
            true -> getString(R.string.unfollow)
            false -> getString(R.string.follow)
        }

        presenter.fetchUserPosts(uuid)
    }

    override fun displayRequestFailure(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
    }

    override fun displayEmptyList() {
        binding?.textProfileEmpty?.visibility = View.VISIBLE
        binding?.rvProfile?.visibility = View.GONE
    }

    override fun displayUserPosts(posts: List<Post>) {
        binding?.textProfileEmpty?.visibility = View.GONE
        binding?.rvProfile?.visibility = View.VISIBLE
        adapter.items = posts
        adapter.notifyDataSetChanged()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_profile_grid ->
                binding?.rvProfile?.layoutManager = GridLayoutManager(requireContext(), 3)
            R.id.menu_profile_list ->
                binding?.rvProfile?.layoutManager = LinearLayoutManager(requireContext())
        }
        return true
    }

    companion object {
        const val KEY_USER_UUID = "key_user_uuid"
    }

}