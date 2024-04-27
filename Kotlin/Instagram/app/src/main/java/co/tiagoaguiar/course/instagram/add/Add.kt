package co.tiagoaguiar.course.instagram.add

import android.net.Uri
import co.tiagoaguiar.course.instagram.commom.base.BasePresenter
import co.tiagoaguiar.course.instagram.commom.base.BaseView

interface Add {
    interface Presenter : BasePresenter {
        fun createPost(uri: Uri, caption: String)

    }

    interface View : BaseView<Presenter> {
        fun showProgress(enabled: Boolean)
        fun displayRequestFailure(message: String)
        fun displayRequestSuccess()

    }
}