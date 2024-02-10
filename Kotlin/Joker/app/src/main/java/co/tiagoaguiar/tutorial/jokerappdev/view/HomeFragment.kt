package co.tiagoaguiar.tutorial.jokerappdev.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.tiagoaguiar.tutorial.jokerappdev.R
import co.tiagoaguiar.tutorial.jokerappdev.model.Category
import co.tiagoaguiar.tutorial.jokerappdev.presentation.HomePresenter
import com.xwray.groupie.GroupieAdapter

class HomeFragment : Fragment(){

    private lateinit var presenter: HomePresenter
    private lateinit var progressBar: ProgressBar
    private val adapter = GroupieAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = HomePresenter(this)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar = view.findViewById(R.id.pb_home)
        val recyclerView = view.findViewById<RecyclerView>( R.id.rv_main)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        presenter.finAllCategories()
        recyclerView.adapter = adapter
    }

    @SuppressLint("NotifyDataSetChanged")
    fun showCategories(categories: List<Category>) {
        val categoriesItem = categories.map { CategoryItem(it) }
        adapter.clear()
        adapter.addAll(categoriesItem)
        adapter.notifyDataSetChanged()
    }
    fun showError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
    fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }
}