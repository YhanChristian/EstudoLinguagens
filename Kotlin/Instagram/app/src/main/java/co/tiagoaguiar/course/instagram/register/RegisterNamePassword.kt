package co.tiagoaguiar.course.instagram.register

import androidx.annotation.StringRes
import co.tiagoaguiar.course.instagram.commom.base.BasePresenter
import co.tiagoaguiar.course.instagram.commom.base.BaseView

interface RegisterNamePassword {

    interface Presenter : BasePresenter {
        fun create(email: String, name: String, password: String, confirm: String)
    }
    interface View : BaseView<Presenter> {
        fun showProgress(enabled: Boolean)
        fun displayNameFailure(@StringRes namError: Int?)
        fun displayPasswordFailure(@StringRes passwordError: Int?)
        fun onCreateSuccess(msg: String)
        fun onCreateFailure(msg: String)
    }
}