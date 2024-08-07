package co.tiagoaguiar.course.instagram.profile.data

import co.tiagoaguiar.course.instagram.commom.base.Cache
import co.tiagoaguiar.course.instagram.commom.model.Post
import co.tiagoaguiar.course.instagram.commom.model.User

class ProfileDataSourceFactory(
    private val profileCache : Cache<Pair<User, Boolean?>>,
    private val postsCache : Cache<List<Post>>
) {
    fun createLocalDataSource(): ProfileDataSource {
        return ProfileLocalDataSource(profileCache, postsCache)
    }

    fun createRemoteDataSource(): ProfileDataSource {
        return ProfileFireDataSource()
    }
    fun createFromUser(uuid: String?) : ProfileDataSource {
        if(uuid != null) {
            return ProfileFireDataSource()
        }
        if(profileCache.isCached()) {
            return ProfileLocalDataSource(profileCache, postsCache)
        }
        return ProfileFireDataSource()
    }

    fun createFromPosts(uuid: String?) : ProfileDataSource {
        if(uuid != null) {
            return ProfileFireDataSource()
        }
        if(postsCache.isCached()) {
            return ProfileLocalDataSource(profileCache, postsCache)
        }
        return ProfileFireDataSource()
    }
}