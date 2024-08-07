package co.tiagoaguiar.course.instagram.login.presentation

import android.util.Patterns
import co.tiagoaguiar.course.instagram.R
import co.tiagoaguiar.course.instagram.commom.model.UserAuth
import co.tiagoaguiar.course.instagram.login.Login
import co.tiagoaguiar.course.instagram.login.data.LoginCallback
import co.tiagoaguiar.course.instagram.login.data.LoginRepository

class LoginPresenter(
    private var view: Login.View?,
    private val repository: LoginRepository
) : Login.Presenter {

    override fun login(email: String, password: String) {
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            view?.displayEmailFailure(R.string.invalid_email)
            return
        }
        if (password.length < 8) {
            view?.displayPasswordFailure(R.string.invalid_password)
            return
        }
        view?.showProgress(true)
        repository.login(email, password, object : LoginCallback {
            override fun onSuccess() {
                view?.onUserAuthenticated()
            }

            override fun onFailure(msg: String) {
                view?.onUserUnauthorized(msg)
            }

            override fun onComplete() {
                view?.showProgress(false)
            }

        })
    }

    override fun onDestroy() {
        view = null
    }
}