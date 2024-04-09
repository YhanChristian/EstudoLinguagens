package co.tiagoaguiar.course.instagram.profile.view

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import co.tiagoaguiar.course.instagram.R
import co.tiagoaguiar.course.instagram.commom.base.BaseFragment
import co.tiagoaguiar.course.instagram.commom.base.DependencyInjector
import co.tiagoaguiar.course.instagram.commom.model.Post
import co.tiagoaguiar.course.instagram.commom.model.UserAuth
import co.tiagoaguiar.course.instagram.databinding.FragmentProfileBinding
import co.tiagoaguiar.course.instagram.profile.Profile
import co.tiagoaguiar.course.instagram.profile.presentation.ProfilePresenter

class ProfileFragment : BaseFragment<FragmentProfileBinding, Profile.Presenter>
    (R.layout.fragment_profile, FragmentProfileBinding::bind), Profile.View {

    override lateinit var presenter: Profile.Presenter
    private val adapter = PostAdapter()

    override fun setupViews() {
        binding?.rvProfile?.layoutManager = GridLayoutManager(requireContext(), 3)
        binding?.rvProfile?.adapter = adapter
        presenter.fetchUserProfile()
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

    override fun displayUserProfile(userAuth: UserAuth) {
        binding?.textProfilePostsCount?.text = userAuth.postCount.toString()
        binding?.textProfileFollowingCount?.text = userAuth.followingCount.toString()
        binding?.textProfileFollowersCount?.text = userAuth.followerCount.toString()
        binding?.textProfileUsername?.text = userAuth.name
        binding?.textProfileUsernameBio?.text = "TODO"
        presenter.fetchUserPosts()
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

}