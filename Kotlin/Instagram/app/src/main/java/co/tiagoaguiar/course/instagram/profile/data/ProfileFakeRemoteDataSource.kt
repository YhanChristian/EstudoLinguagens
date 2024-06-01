package co.tiagoaguiar.course.instagram.profile.data

import android.os.Handler
import android.os.Looper
import co.tiagoaguiar.course.instagram.commom.base.RequestCallback
import co.tiagoaguiar.course.instagram.commom.model.Database
import co.tiagoaguiar.course.instagram.commom.model.Post
import co.tiagoaguiar.course.instagram.commom.model.UserAuth

class ProfileFakeRemoteDataSource : ProfileDataSource {
    override fun fetchUserProfile(userUUID: String, callback: RequestCallback<Pair<UserAuth, Boolean?>>) {
        Handler(Looper.getMainLooper()).postDelayed({
            val userAuth = Database.usersAuth.firstOrNull() {userUUID == it.uuid}
            if(userAuth != null){
                if(userAuth == Database.sessionAuth){
                    callback.onSuccess(Pair(userAuth, null))
                } else {
                    val followings = Database.followers[Database.sessionAuth!!.uuid]
                    val destUser = followings?.firstOrNull { it == userUUID }
                    callback.onSuccess(Pair(userAuth, destUser != null))
                }
            } else {
                callback.onFailure("Usuário não encontrado!")
            }

            callback.onComplete()

        }, 1000)
    }

    override fun fetchUserPosts(userUUID: String, callback: RequestCallback<List<Post>>) {
        Handler(Looper.getMainLooper()).postDelayed({
            val posts = Database.posts[userUUID]
            callback.onSuccess(posts?.toList() ?: emptyList())
            callback.onComplete()

        }, 1000)
    }
}