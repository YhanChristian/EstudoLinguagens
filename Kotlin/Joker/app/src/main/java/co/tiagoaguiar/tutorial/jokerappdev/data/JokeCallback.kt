package co.tiagoaguiar.tutorial.jokerappdev.data

import co.tiagoaguiar.tutorial.jokerappdev.model.Joke

interface JokeCallback {
    fun onSuccess(response: Joke)
    fun onError(message: String)
    fun onComplete()
}