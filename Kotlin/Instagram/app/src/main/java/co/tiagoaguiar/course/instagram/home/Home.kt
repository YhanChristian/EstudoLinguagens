package co.tiagoaguiar.course.instagram.home

import co.tiagoaguiar.course.instagram.commom.base.BasePresenter
import co.tiagoaguiar.course.instagram.commom.base.BaseView

interface Home {

    interface View : BaseView<Presenter> {
    }
    interface Presenter : BasePresenter {
    }
}