package co.tiagoaguiar.tutorial.jokerappdev.data

import android.os.Handler
import android.os.Looper
import co.tiagoaguiar.tutorial.jokerappdev.model.Joke
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.RuntimeException

class JokeRemoteDataSource {
    fun findByCategoryName(category: String, callback: JokeCallback) {
        HTTPClient.retrofit()
            .create((ChuckNorrisAPI::class.java))
            .findByCategoryName(category)
            .enqueue(object : Callback<Joke> {
                override fun onResponse(call: Call<Joke>, response: Response<Joke>) {
                    Handler(Looper.getMainLooper()).post {
                        if (response.isSuccessful) {
                            callback.onSuccess(response.body()!!)
                        } else {
                            callback.onError(response.errorBody()?.string() ?: throw RuntimeException("Piada não encontrada"))
                        }
                        callback.onComplete()
                    }
                }

                override fun onFailure(call: Call<Joke>, t: Throwable) {
                    Handler(Looper.getMainLooper()).post {
                        callback.onError(t.message ?: "Erro interno")
                        callback.onComplete()
                    }
                }
            })
    }
}