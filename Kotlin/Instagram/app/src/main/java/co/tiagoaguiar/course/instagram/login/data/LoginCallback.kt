package co.tiagoaguiar.course.instagram.login.data

import co.tiagoaguiar.course.instagram.commom.model.UserAuth

interface LoginCallback {
    fun onSuccess(userAuth: UserAuth)
    fun onFailure(msg: String)
    fun onComplete()
}