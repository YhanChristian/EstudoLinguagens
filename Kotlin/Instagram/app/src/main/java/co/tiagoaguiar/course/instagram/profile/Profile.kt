package co.tiagoaguiar.course.instagram.profile

import co.tiagoaguiar.course.instagram.commom.base.BasePresenter
import co.tiagoaguiar.course.instagram.commom.base.BaseView
import co.tiagoaguiar.course.instagram.commom.model.Post
import co.tiagoaguiar.course.instagram.commom.model.User

interface Profile {
    interface View : BaseView<Presenter> {
        fun showProgress(enabled: Boolean)
        fun displayUserProfile(userAuth: Pair<User, Boolean?>)
        fun displayRequestFailure(msg: String)
        fun displayEmptyList()
        fun displayUserPosts(posts: List<Post>)
        fun followUpdated()
        interface FollowListener {
            fun followUpdated()
        }
    }
    interface Presenter : BasePresenter {
        fun fetchUserProfile(uuid: String?)
        fun fetchUserPosts(uuid: String?)
        fun followUser(uuid: String?, follow: Boolean)
        fun clear()

    }
}