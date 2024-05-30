package co.tiagoaguiar.course.instagram.profile.data

import co.tiagoaguiar.course.instagram.commom.base.Cache
import co.tiagoaguiar.course.instagram.commom.model.Post
import co.tiagoaguiar.course.instagram.commom.model.UserAuth

class ProfileDataSourceFactory(
    private val profileCache : Cache<UserAuth>,
    private val postsCache : Cache<List<Post>>
) {
    fun createLocalDataSource(): ProfileDataSource {
        return ProfileLocalDataSource(profileCache, postsCache)
    }
    fun createFromUser(uuid: String?) : ProfileDataSource {
        if(uuid != null) {
            return ProfileFakeRemoteDataSource()
        }
        if(profileCache.isCached()) {
            return ProfileLocalDataSource(profileCache, postsCache)
        }
        return ProfileFakeRemoteDataSource()
    }

    fun createFromPosts(uuid: String?) : ProfileDataSource {
        if(uuid != null) {
            return ProfileFakeRemoteDataSource()
        }
        if(postsCache.isCached()) {
            return ProfileLocalDataSource(profileCache, postsCache)
        }
        return ProfileFakeRemoteDataSource()
    }
}