package co.tiagoaguiar.course.instagram.profile.data

import co.tiagoaguiar.course.instagram.commom.base.RequestCallback
import co.tiagoaguiar.course.instagram.commom.model.Post
import co.tiagoaguiar.course.instagram.commom.model.UserAuth

interface ProfileDataSource {
    fun fetchUserProfile(userUUID: String,callback: RequestCallback<Pair<UserAuth, Boolean?>>)
    fun fetchUserPosts(userUUID: String, callback: RequestCallback<List<Post>>)

    fun followUser(userUUID: String, isFollow: Boolean, callback: RequestCallback<Boolean>) {
        throw UnsupportedOperationException("Not implemented")
    }
    fun fetchSession() : UserAuth {throw UnsupportedOperationException("Not implemented")}
    fun putUser(response: Pair<UserAuth, Boolean?>) {throw UnsupportedOperationException("Not implemented")}
    fun putPosts(response: List<Post>?) {throw UnsupportedOperationException("Not implemented")}

}