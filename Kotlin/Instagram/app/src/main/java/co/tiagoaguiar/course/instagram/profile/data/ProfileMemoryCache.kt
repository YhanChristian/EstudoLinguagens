package co.tiagoaguiar.course.instagram.profile.data

import co.tiagoaguiar.course.instagram.commom.base.Cache
import co.tiagoaguiar.course.instagram.commom.model.UserAuth

object ProfileMemoryCache : Cache<Pair<UserAuth, Boolean?>> {
    private var userAuth: Pair<UserAuth, Boolean?>? = null
    override fun isCached(): Boolean {
        return userAuth != null
    }

    override fun get(key: String): Pair<UserAuth, Boolean?>? {
        if(userAuth?.first?.uuid == key) {
            return userAuth
        }
        return null
    }

    override fun put(data: Pair<UserAuth, Boolean?>?) {
        this.userAuth = data
    }
}