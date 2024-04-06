package co.tiagoaguiar.tutorial.jokerappdev.data

import android.os.Handler
import android.os.Looper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryRemoteDataSource {
    fun findAllCategories(callback: ListCategoryCallback){
        HTTPClient.retrofit()
            .create(ChuckNorrisAPI::class.java)
            .findAllCategories()
            .enqueue(object : Callback<List<String>> {
                override fun onResponse(
                    call: Call<List<String>>,
                    response: Response<List<String>>
                ) {
                    Handler(Looper.getMainLooper()).post {
                        if(response.isSuccessful) {
                            callback.onSuccess(response.body() ?: emptyList())
                        } else {
                            callback.onError(response.errorBody()?.string() ?: "Erro desconhecido")
                        }
                        callback.onComplete()
                    }
                }

                override fun onFailure(call: Call<List<String>>, t: Throwable) {
                    Handler(Looper.getMainLooper()).post {
                        callback.onError(t.message ?: "Erro interno")
                        callback.onComplete()
                    }
                }
            })
    }

}