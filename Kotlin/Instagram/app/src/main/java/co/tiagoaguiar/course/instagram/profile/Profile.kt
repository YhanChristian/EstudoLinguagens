package co.tiagoaguiar.course.instagram.profile

import co.tiagoaguiar.course.instagram.commom.base.BasePresenter
import co.tiagoaguiar.course.instagram.commom.base.BaseView
import co.tiagoaguiar.course.instagram.commom.model.Post
import co.tiagoaguiar.course.instagram.commom.model.UserAuth

interface Profile {
    interface View : BaseView<Presenter> {
        fun showProgress(enabled: Boolean)
        fun displayUserProfile(userAuth: UserAuth)
        fun displayRequestFailure(msg: String)
        fun displayEmptyList()
        fun displayUserPosts(posts: List<Post>)
    }
    interface Presenter : BasePresenter {
        fun fetchUserProfile()
        fun fetchUserPosts()

    }
}