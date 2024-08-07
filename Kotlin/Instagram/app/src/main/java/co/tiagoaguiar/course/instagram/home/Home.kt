package co.tiagoaguiar.course.instagram.home

import co.tiagoaguiar.course.instagram.commom.base.BasePresenter
import co.tiagoaguiar.course.instagram.commom.base.BaseView
import co.tiagoaguiar.course.instagram.commom.model.Post
import co.tiagoaguiar.course.instagram.commom.model.UserAuth

interface Home {

    interface View : BaseView<Presenter> {
        fun showProgress(enabled: Boolean)
        fun displayRequestFailure(msg: String)
        fun displayEmptyList()
        fun displayFullPosts(posts: List<Post>)
    }
    interface Presenter : BasePresenter {
        fun fetchFeed()
        fun clear()
        fun logout()
    }
}