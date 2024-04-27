package co.tiagoaguiar.course.instagram.add.data


import android.net.Uri
import android.os.Handler
import android.os.Looper
import co.tiagoaguiar.course.instagram.commom.base.RequestCallback
import co.tiagoaguiar.course.instagram.commom.model.Database
import co.tiagoaguiar.course.instagram.commom.model.Post
import java.util.UUID

class AddFakeRemoteDataSource : AddDataSource {
    override fun createPost(
        userUUID: String,
        uri: Uri,
        caption: String,
        callback: RequestCallback<Boolean>
    ) {
        Handler(Looper.getMainLooper()).postDelayed({
            var posts = Database.posts[userUUID]
            if(posts == null) {
                posts = mutableSetOf()
                Database.posts[userUUID] = posts
            }

            var followers = Database.followers[userUUID]
            if(followers == null) {
                followers = mutableSetOf()
                Database.followers[userUUID] = followers
            }


            Database.sessionAuth?.let {
                val post = Post(UUID.randomUUID().toString(),
                    uri,
                    caption,
                    System.currentTimeMillis(),
                    it)
                posts.add(post)

                if(followers.isNotEmpty()) {
                    for(follower in followers) {
                        Database.feeds[follower.uuid]?.add(post)
                    }
                }
            }
            callback.onSuccess(true)
        }, 1000)
    }
}