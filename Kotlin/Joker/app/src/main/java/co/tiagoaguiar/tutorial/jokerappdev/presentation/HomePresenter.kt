package co.tiagoaguiar.tutorial.jokerappdev.presentation

import android.graphics.Color
import android.os.Looper
import co.tiagoaguiar.tutorial.jokerappdev.data.CategoryRemoteDataSource
import co.tiagoaguiar.tutorial.jokerappdev.data.ListCategoryCallback
import co.tiagoaguiar.tutorial.jokerappdev.model.Category
import co.tiagoaguiar.tutorial.jokerappdev.view.HomeFragment

class HomePresenter(
    private val view: HomeFragment,
    private val dataSource: CategoryRemoteDataSource = CategoryRemoteDataSource()
) : ListCategoryCallback {
    fun findAllCategories() {
        view.showProgressBar()
        dataSource.findAllCategories(this)
    }

    override fun onSuccess(response: List<String>) {
        val start = 40
        val end = 190
        val delta = (end - start) / response.size
        val categories = response.mapIndexed { index, it ->
            val hsv = floatArrayOf(
                start + (delta * index).toFloat(),
                100.0f,
                100.0f
            )
            Category(it, Color.HSVToColor(hsv).toLong())
        }
        view.showCategories(categories)
    }

    override fun onComplete() {
        view.hideProgressBar()
    }

    override fun onError(message: String) {
        view.showError(message)
    }
}