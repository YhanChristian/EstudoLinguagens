package co.tiagoaguiar.course.instagram.home.data

import co.tiagoaguiar.course.instagram.commom.base.Cache
import co.tiagoaguiar.course.instagram.commom.base.RequestCallback
import co.tiagoaguiar.course.instagram.commom.model.Database
import co.tiagoaguiar.course.instagram.commom.model.Post
import co.tiagoaguiar.course.instagram.commom.model.UserAuth

class HomeLocalDataSource(
    private val feedCache : Cache<List<Post>>
) : HomeDataSource {
    override fun fetchFeed(userUUID: String, callback: RequestCallback<List<Post>>) {
        val posts = feedCache.get(userUUID)
        posts?.let { callback.onSuccess(it) } ?: callback.onFailure("Nenhum post encontrado")
        callback.onComplete()
    }
    override fun fetchSession(): UserAuth {
        return Database.sessionAuth ?: throw RuntimeException("User not logged in")
    }
    override fun putFeed(response: List<Post>) {
        feedCache.put(response)
    }

}