package co.tiagoaguiar.course.instagram.search

import co.tiagoaguiar.course.instagram.commom.base.BasePresenter
import co.tiagoaguiar.course.instagram.commom.base.BaseView
import co.tiagoaguiar.course.instagram.commom.model.User
import co.tiagoaguiar.course.instagram.commom.model.UserAuth

interface Search {
    interface Presenter : BasePresenter {
        fun fetchUsers(name: String)
    }
    interface View : BaseView<Presenter>{
        fun showProgress(enabled: Boolean)
        fun displayFullUsers(users: List<User>)
        fun displayEmptyUsers()
    }
}