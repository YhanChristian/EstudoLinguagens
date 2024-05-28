package co.tiagoaguiar.course.instagram.search.view

import android.app.SearchManager
import android.content.Context
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import co.tiagoaguiar.course.instagram.R
import co.tiagoaguiar.course.instagram.commom.base.BaseFragment
import co.tiagoaguiar.course.instagram.commom.base.DependencyInjector
import co.tiagoaguiar.course.instagram.commom.model.UserAuth
import co.tiagoaguiar.course.instagram.databinding.FragmentSearchBinding
import co.tiagoaguiar.course.instagram.search.Search
import co.tiagoaguiar.course.instagram.search.presentation.SearchPresenter

class SearchFragment : BaseFragment<FragmentSearchBinding, Search.Presenter>(
    R.layout.fragment_search,
    FragmentSearchBinding::bind
), Search.View {

    override lateinit var presenter: Search.Presenter
    private val adapter = SearchAdapter()

    override fun setupViews() {
        binding?.rvSearch?.layoutManager = LinearLayoutManager(requireContext())
        binding?.rvSearch?.adapter = adapter
    }

    override fun setupPresenter() {
        val repository = DependencyInjector.searchRepository()
        presenter = SearchPresenter(this, repository)
    }

    override fun getMenu() = R.menu.menu_search

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        val searchManager = requireActivity().getSystemService(Context.SEARCH_SERVICE)
                as SearchManager
        val searchView = (menu.findItem(R.id.menu_search).actionView as SearchView)
        searchView.apply {
            setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))
            setOnQueryTextListener(object: SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if(newText?.isNotEmpty() == true) {
                        presenter.fetchUsers(newText)
                        return true
                    }
                    return false
                }
            })
        }
    }
    override fun showProgress(enabled: Boolean) {
        binding?.progressSearch?.visibility = if (enabled) View.VISIBLE else View.GONE
    }
    override fun displayFullUsers(users: List<UserAuth>) {
        binding?.textSearchEmpty?.visibility = View.GONE
        binding?.rvSearch?.visibility = View.VISIBLE
        adapter.items = users
        adapter.notifyDataSetChanged()
    }
    override fun displayEmptyUsers() {
        binding?.textSearchEmpty?.visibility = View.VISIBLE
        binding?.rvSearch?.visibility = View.GONE
    }

}