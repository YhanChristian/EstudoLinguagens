package co.tiagoaguiar.course.instagram.home.data

import co.tiagoaguiar.course.instagram.commom.base.Cache
import co.tiagoaguiar.course.instagram.commom.model.Post
import co.tiagoaguiar.course.instagram.commom.model.UserAuth
import co.tiagoaguiar.course.instagram.profile.data.ProfileCache
import co.tiagoaguiar.course.instagram.profile.data.ProfileDataSource
import co.tiagoaguiar.course.instagram.profile.data.ProfileFakeRemoteDataSource
import co.tiagoaguiar.course.instagram.profile.data.ProfileLocalDataSource

class HomeDataSourceFactory(
    private val feedCache : Cache<List<Post>>
) {
    fun createLocalDataSource(): HomeDataSource {
        return HomeLocalDataSource(feedCache)
    }

    fun createFromFeed() : HomeDataSource {
        if(feedCache.isCached()) {
            return HomeLocalDataSource(feedCache)
        }
        return HomeFakeRemoteDataSource()
    }
}