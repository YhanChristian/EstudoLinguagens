package co.tiagoaguiar.course.instagram.profile.data

import co.tiagoaguiar.course.instagram.commom.base.Cache
import co.tiagoaguiar.course.instagram.commom.model.User
import co.tiagoaguiar.course.instagram.commom.model.UserAuth

object ProfileMemoryCache : Cache<Pair<User, Boolean?>> {
    private var userAuth: Pair<User, Boolean?>? = null
    override fun isCached(): Boolean {
        return userAuth != null
    }

    override fun get(key: String): Pair<User, Boolean?>? {
        if(userAuth?.first?.uuid == key) {
            return userAuth
        }
        return null
    }

    override fun put(data: Pair<User, Boolean?>?) {
        this.userAuth = data
    }
}