package co.tiagoaguiar.course.instagram.home.view

import android.content.Context
import android.os.Bundle
import android.system.Os.bind
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.tiagoaguiar.course.instagram.R
import co.tiagoaguiar.course.instagram.commom.base.BaseFragment
import co.tiagoaguiar.course.instagram.commom.base.DependencyInjector
import co.tiagoaguiar.course.instagram.commom.model.Post
import co.tiagoaguiar.course.instagram.databinding.FragmentHomeBinding
import co.tiagoaguiar.course.instagram.home.Home
import co.tiagoaguiar.course.instagram.home.presenter.HomePresenter
import co.tiagoaguiar.course.instagram.main.LogoutListener
import co.tiagoaguiar.course.instagram.profile.view.PostAdapter

class HomeFragment : BaseFragment<FragmentHomeBinding, Home.Presenter>(
    R.layout.fragment_home,
    FragmentHomeBinding::bind
), Home.View {
    override lateinit var presenter: Home.Presenter
    private val adapter = FeedAdapter()
    private var logoutListener : LogoutListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is LogoutListener) {
            logoutListener = context
        }
    }

    override fun setupViews() {
        binding?.rvHome?.layoutManager = LinearLayoutManager(requireContext())
        binding?.rvHome?.adapter = adapter
        presenter.fetchFeed()
    }

    override fun setupPresenter() {
        val repository = DependencyInjector.homeRepository()
        presenter = HomePresenter(this, repository)
    }

    override fun getMenu() = R.menu.menu_profile

    override fun showProgress(enabled: Boolean) {
        binding?.progressHome?.visibility = if (enabled) View.VISIBLE else View.GONE
    }

    override fun displayRequestFailure(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
    }

    override fun displayEmptyList() {
        binding?.textHomeEmpty?.visibility = View.VISIBLE
        binding?.rvHome?.visibility = View.GONE
    }

    override fun displayFullPosts(posts: List<Post>) {
        binding?.textHomeEmpty?.visibility = View.GONE
        binding?.rvHome?.visibility = View.VISIBLE
        adapter.items = posts
        adapter.notifyDataSetChanged()
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

}