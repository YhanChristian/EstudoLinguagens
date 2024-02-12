package co.tiagoaguiar.tutorial.jokerappdev.presentation

import co.tiagoaguiar.tutorial.jokerappdev.data.JokeCallback
import co.tiagoaguiar.tutorial.jokerappdev.data.JokeRemoteDataSource
import co.tiagoaguiar.tutorial.jokerappdev.model.Joke
import co.tiagoaguiar.tutorial.jokerappdev.view.JokeFragment

class JokePresenter(private val view: JokeFragment,
    private val dataSource: JokeRemoteDataSource = JokeRemoteDataSource()) : JokeCallback {
    fun findByCategoryName(category: String) {
        view.showProgressBar()
        dataSource.findByCategoryName(category, this)
    }

    override fun onSuccess(response: Joke) {
        view.showJoke(response)
    }

    override fun onError(message: String) {
        view.showError(message)
    }

    override fun onComplete() {
        view.hideProgressBar()
    }

}