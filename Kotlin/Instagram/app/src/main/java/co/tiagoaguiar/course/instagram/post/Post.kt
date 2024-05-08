package co.tiagoaguiar.course.instagram.post

import android.net.Uri
import co.tiagoaguiar.course.instagram.commom.base.BasePresenter
import co.tiagoaguiar.course.instagram.commom.base.BaseView

interface Post {

    interface Presenter : BasePresenter {
        fun fetchPictures()
        fun selectPicture(uri: Uri)
        fun getSelectedPicture() : Uri?
    }
    interface View : BaseView<Presenter> {
        fun showProgress(enabled: Boolean)
        fun displayFullPictures(pictures: List<Uri>)
        fun displayEmptyPictures()
        fun displayRequestFailure(msg: String)
    }
}