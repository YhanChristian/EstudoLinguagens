package co.tiagoaguiar.tutorial.jokerappdev.data

import android.os.Handler
import android.os.Looper

class CategoryRemoteDataSource {
    fun findAllCategories(callback: ListCategoryCallback){
        Handler(Looper.getMainLooper()).postDelayed({
            val response = arrayListOf(
                "Programming",
                "Misc",
                "Pun",
                "Spooky",
                "Christmas"
            )
            callback.onSuccess(response)
            callback.onComplete()
        }, 2000)
    }

}