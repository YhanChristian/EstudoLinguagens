package co.tiagoaguiar.course.instagram.home.data

import co.tiagoaguiar.course.instagram.commom.base.Cache
import co.tiagoaguiar.course.instagram.commom.model.Post

object FeedMemoryCache : Cache<List<Post>> {

    private var posts : List<Post>? = null
    override fun isCached(): Boolean {
        return posts != null
    }

    override fun get(key: String): List<Post>? {
        return posts
    }
    override fun put(data: List<Post>) {
        this.posts = data
    }
}