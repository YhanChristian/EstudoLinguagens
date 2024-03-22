package co.tiagoaguiar.course.instagram.register

import android.net.Uri
import androidx.annotation.StringRes
import co.tiagoaguiar.course.instagram.commom.base.BasePresenter
import co.tiagoaguiar.course.instagram.commom.base.BaseView

interface RegisterPhoto {

    interface Presenter : BasePresenter {
        fun updateUser(photoUri : Uri)
    }
    interface View : BaseView<Presenter> {
        fun showProgress(enabled: Boolean)
        fun onUpdateFailure(msg: String)
        fun onUpdateSuccess()
    }
}