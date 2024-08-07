package co.tiagoaguiar.course.instagram.profile.data

import co.tiagoaguiar.course.instagram.commom.base.Cache
import co.tiagoaguiar.course.instagram.commom.base.RequestCallback
import co.tiagoaguiar.course.instagram.commom.model.Post
import co.tiagoaguiar.course.instagram.commom.model.User
import com.google.firebase.auth.FirebaseAuth

class ProfileLocalDataSource(
    private val profileCache : Cache<Pair<User, Boolean?>>,
    private val postsCache : Cache<List<Post>>
) : ProfileDataSource {
    override fun fetchUserProfile(userUUID: String, callback: RequestCallback<Pair<User, Boolean?>>) {
        val userAuth = profileCache.get(userUUID)
        userAuth?.let { callback.onSuccess(it) } ?: callback.onFailure("Usuario não encontrado")
        callback.onComplete()
    }

    override fun fetchUserPosts(userUUID: String, callback: RequestCallback<List<Post>>) {
        val posts = postsCache.get(userUUID)
        posts?.let { callback.onSuccess(it) } ?: callback.onFailure("Nenhum post encontrado")
        callback.onComplete()
    }

    override fun fetchSession(): String {
        return FirebaseAuth.getInstance().uid ?: throw RuntimeException("User not logged in")
    }

    override fun putUser(response: Pair<User, Boolean?>?) {
        profileCache.put(response)
    }

    override fun putPosts(response: List<Post>?) {
        postsCache.put(response)
    }
}