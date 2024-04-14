package co.tiagoaguiar.course.instagram.profile.data

import co.tiagoaguiar.course.instagram.commom.model.Post

object PostListMemoryCache : ProfileCache<List<Post>> {

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