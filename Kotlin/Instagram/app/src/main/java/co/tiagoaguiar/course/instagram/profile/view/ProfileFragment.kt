package co.tiagoaguiar.course.instagram.profile.view

import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import co.tiagoaguiar.course.instagram.R
import co.tiagoaguiar.course.instagram.commom.base.BaseFragment
import co.tiagoaguiar.course.instagram.commom.base.DependencyInjector
import co.tiagoaguiar.course.instagram.commom.model.Post
import co.tiagoaguiar.course.instagram.commom.model.User
import co.tiagoaguiar.course.instagram.commom.model.UserAuth
import co.tiagoaguiar.course.instagram.databinding.FragmentProfileBinding
import co.tiagoaguiar.course.instagram.main.LogoutListener
import co.tiagoaguiar.course.instagram.profile.Profile
import co.tiagoaguiar.course.instagram.profile.presentation.ProfilePresenter
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView

class ProfileFragment : BaseFragment<FragmentProfileBinding, Profile.Presenter>
    (R.layout.fragment_profile, FragmentProfileBinding::bind),
    Profile.View, BottomNavigationView.OnNavigationItemSelectedListener {

    override lateinit var presenter: Profile.Presenter
    private val adapter = PostAdapter()
    private var uuid: String? = null
    private var logoutListener : LogoutListener? = null
    private var followListener : Profile.View.FollowListener? = null


    override fun onAttach(context: Context) {
        if(context is LogoutListener) {
            logoutListener = context
        }
        if(context is Profile.View.FollowListener) {
            followListener = context
        }
        super.onAttach(context)
    }

    override fun setupViews() {
        uuid = arguments?.getString(KEY_USER_UUID)
        binding?.rvProfile?.layoutManager = GridLayoutManager(requireContext(), 3)
        binding?.rvProfile?.adapter = adapter
        binding?.navProfileTabs?.setOnNavigationItemSelectedListener(this)

        binding?.buttonEditProfile?.setOnClickListener {
            val following = binding?.buttonEditProfile?.tag as Boolean?
            when (following) {
                true -> {
                    binding?.buttonEditProfile?.text = getString(R.string.follow)
                    binding?.buttonEditProfile?.tag = false
                    presenter.followUser(uuid, false)
                }
                false -> {
                    binding?.buttonEditProfile?.text = getString(R.string.unfollow)
                    binding?.buttonEditProfile?.tag = true
                    presenter.followUser(uuid, true)
                }
            }
        }
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

    override fun displayUserProfile(userAuth: Pair<User, Boolean?>) {
        val (user, following) = userAuth
        binding?.textProfilePostsCount?.text = user.postCount.toString()
        binding?.textProfileFollowingCount?.text = user.following.toString()
        binding?.textProfileFollowersCount?.text = user.followers.toString()
        binding?.textProfileUsername?.text = user.name
        binding?.textProfileUsernameBio?.text = "TODO"
        binding?.let {
            Glide.with(requireContext()).load(user.photoUrl).into(it.imageProfileIcon)
        }

        binding?.buttonEditProfile?.text = when (following) {
            null -> getString(R.string.edit_profile)
            true -> getString(R.string.unfollow)
            false -> getString(R.string.follow)
        }
        binding?.buttonEditProfile?.tag = following

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

    override fun followUpdated() {
        followListener?.followUpdated()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_profile_grid ->
                binding?.rvProfile?.layoutManager = GridLayoutManager(requireContext(), 3)

            R.id.menu_profile_list ->
                binding?.rvProfile?.layoutManager = LinearLayoutManager(requireContext())
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_logout -> {
                logoutListener?.logout()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val KEY_USER_UUID = "key_user_uuid"
    }

}