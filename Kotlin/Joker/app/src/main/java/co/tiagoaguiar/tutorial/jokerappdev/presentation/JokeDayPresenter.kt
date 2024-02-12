package co.tiagoaguiar.tutorial.jokerappdev.presentation

import co.tiagoaguiar.tutorial.jokerappdev.data.JokeCallback
import co.tiagoaguiar.tutorial.jokerappdev.data.JokeDayRemoteDataSource
import co.tiagoaguiar.tutorial.jokerappdev.model.Joke
import co.tiagoaguiar.tutorial.jokerappdev.view.JokeDayFragment

class JokeDayPresenter(private val view: JokeDayFragment,
                    private val dataSource: JokeDayRemoteDataSource = JokeDayRemoteDataSource()
) : JokeCallback {
    fun findJokeDay() {
        view.showProgressBar()
        dataSource.findJokeDay(this)
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
