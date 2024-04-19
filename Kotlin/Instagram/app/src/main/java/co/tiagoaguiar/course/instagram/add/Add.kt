package co.tiagoaguiar.course.instagram.add

import co.tiagoaguiar.course.instagram.commom.base.BasePresenter
import co.tiagoaguiar.course.instagram.commom.base.BaseView

interface Add {
    interface Presenter : BasePresenter {

    }

    interface View : BaseView<Presenter> {

    }
}