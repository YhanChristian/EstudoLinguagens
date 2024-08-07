package co.tiagoaguiar.course.instagram.home.data

import co.tiagoaguiar.course.instagram.commom.base.RequestCallback
import co.tiagoaguiar.course.instagram.commom.model.Post

class HomeRepository(private val dataSourceFactory: HomeDataSourceFactory) {
    fun fetchFeed(callback: RequestCallback<List<Post>>) {
        val localDataSource = dataSourceFactory.createLocalDataSource()
        val userID = localDataSource.fetchSession()
        val dataSource = dataSourceFactory.createFromFeed()
        dataSource.fetchFeed(userID, object : RequestCallback<List<Post>> {
            override fun onSuccess(data: List<Post>) {
                localDataSource.putFeed(data)
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
        localDataSource.putFeed(null)
    }

    fun logout() {
        dataSourceFactory.createRemoteDataSource().logout()
    }
}