package co.tiagoaguiar.course.instagram.profile.data

import co.tiagoaguiar.course.instagram.commom.base.RequestCallback
import co.tiagoaguiar.course.instagram.commom.model.Post
import co.tiagoaguiar.course.instagram.commom.model.UserAuth

class ProfileRepository(private val dataSourceFactory: ProfileDataSourceFactory) {
    fun fetchUserProfile(uuid: String?, callback: RequestCallback<Pair<UserAuth, Boolean?>>) {
        val localDataSource = dataSourceFactory.createLocalDataSource()
        val userId = uuid ?: localDataSource.fetchSession().uuid
        val dataSource = dataSourceFactory.createFromUser(userId)
        dataSource.fetchUserProfile(userId, object : RequestCallback<Pair<UserAuth,Boolean?>> {
            override fun onSuccess(data: Pair<UserAuth, Boolean?>) {
                if(uuid == null) localDataSource.putUser(data)
                callback.onSuccess(data)
            }

            override fun onFailure(msg: String) {
                callback.onFailure(msg)
            }

            override fun onComplete() {
                callback.onComplete()
            }

        })
    }
    fun fetchUserPosts(uuid: String?, callback: RequestCallback<List<Post>>) {
        val localDataSource = dataSourceFactory.createLocalDataSource()
        val userId = uuid ?: localDataSource.fetchSession().uuid
        val dataSource = dataSourceFactory.createFromPosts(userId)
        dataSource.fetchUserPosts(userId, object : RequestCallback<List<Post>> {
            override fun onSuccess(data: List<Post>) {
                if(uuid == null) localDataSource.putPosts(data)
                callback.onSuccess(data)
            }

            override fun onFailure(msg: String) {
                callback.onFailure(msg)
            }

            override fun onComplete() {
                callback.onComplete()
            }

        })
    }

    fun followUser(uuid: String?, isFollow: Boolean, callback: RequestCallback<Boolean>) {
        val localDataSource = dataSourceFactory.createLocalDataSource()
        val userId = uuid ?: localDataSource.fetchSession().uuid
        val dataSource = dataSourceFactory.createFromUser(userId)
        dataSource.followUser(userId, isFollow, object : RequestCallback<Boolean> {
            override fun onSuccess(data: Boolean) {
                callback.onSuccess(data)
            }

            override fun onFailure(msg: String) {
                callback.onFailure(msg)
            }

            override fun onComplete() {
               callback.onComplete()
            }
        })
    }


    fun clearCache() {
        val localDataSource = dataSourceFactory.createLocalDataSource()
        localDataSource.putPosts(null)
    }
}