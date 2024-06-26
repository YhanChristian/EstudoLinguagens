package co.tiagoaguiar.course.instagram.home.data

import co.tiagoaguiar.course.instagram.commom.base.RequestCallback
import co.tiagoaguiar.course.instagram.commom.model.Post
import co.tiagoaguiar.course.instagram.commom.model.UserAuth

interface HomeDataSource {
    fun fetchFeed(userUUID: String, callback: RequestCallback<List<Post>>)
    fun fetchSession() : UserAuth { throw RuntimeException("Not implemented") }
    fun putFeed(response: List<Post>?) { throw RuntimeException("Not implemented") }

}