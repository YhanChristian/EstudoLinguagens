package co.tiagoaguiar.course.instagram.commom.base

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.annotation.LayoutRes
import androidx.annotation.MenuRes
import androidx.fragment.app.Fragment

abstract class BaseFragment<T, P : BasePresenter>
    (@LayoutRes val layoutId: Int, private val bind: (View) -> T) : Fragment(layoutId) {
    abstract var presenter: P
    protected var binding: T? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = bind(view)
        if(savedInstanceState == null) {
            setupViews()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getMenu()?.let { setHasOptionsMenu(true) }
        setupPresenter()
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        getMenu()?.let { inflater.inflate(it, menu) }
    }

    override fun onDestroy() {
        binding = null
        presenter.onDestroy()
        super.onDestroy()
    }
    abstract fun setupViews()

    abstract fun setupPresenter()
    @MenuRes
    open fun getMenu(): Int? {
        return null
    }
}