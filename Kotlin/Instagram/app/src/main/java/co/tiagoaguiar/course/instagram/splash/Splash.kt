package co.tiagoaguiar.course.instagram.splash

import co.tiagoaguiar.course.instagram.commom.base.BasePresenter
import co.tiagoaguiar.course.instagram.commom.base.BaseView

interface Splash {
    interface Presenter : BasePresenter {
        fun authenticated()
    }
    interface View : BaseView<Presenter> {
        fun goToMainScreen()
        fun goToLoginScreen()
    }
}