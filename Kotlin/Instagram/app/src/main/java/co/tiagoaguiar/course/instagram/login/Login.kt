package co.tiagoaguiar.course.instagram.login

import androidx.annotation.StringRes
import co.tiagoaguiar.course.instagram.commom.base.BasePresenter
import co.tiagoaguiar.course.instagram.commom.base.BaseView

interface Login {

    interface Model {

    }

    interface Presenter : BasePresenter {
        fun login(email: String, password: String)
    }
    interface View : BaseView<Presenter> {
        fun showProgress(enabled: Boolean)
        fun displayEmailFailure(@StringRes emailError: Int?)
        fun displayPasswordFailure(@StringRes passwordError: Int?)
        fun onUserAuthenticated()
        fun onUserUnauthorized(msg: String)

    }
}