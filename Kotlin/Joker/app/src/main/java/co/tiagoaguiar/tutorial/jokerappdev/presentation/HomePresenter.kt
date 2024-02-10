package co.tiagoaguiar.tutorial.jokerappdev.presentation

import android.os.Handler
import android.os.Looper
import co.tiagoaguiar.tutorial.jokerappdev.data.CategoryRemoteDataSource
import co.tiagoaguiar.tutorial.jokerappdev.data.ListCategoryCallback
import co.tiagoaguiar.tutorial.jokerappdev.model.Category
import co.tiagoaguiar.tutorial.jokerappdev.view.HomeFragment

class HomePresenter(private val view: HomeFragment,
    private val dataSource: CategoryRemoteDataSource = CategoryRemoteDataSource()
) : ListCategoryCallback{
    fun finAllCategories() {
        view.showProgressBar()
        dataSource.findAllCategories(this)
    }
   override fun onSuccess(response: List<String>) {
        val categories = response.map { Category(it, 0XFFEADAD1) }
        view.showCategories(categories)
    }

   override fun onComplete() {
        view.hideProgressBar()
    }
   override fun onError(message: String) {
        view.showError(message)
    }
}