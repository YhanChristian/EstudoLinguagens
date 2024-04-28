package co.tiagoaguiar.course.instagram.add.presentation

import android.net.Uri
import co.tiagoaguiar.course.instagram.add.Add
import co.tiagoaguiar.course.instagram.add.data.AddRepository
import co.tiagoaguiar.course.instagram.commom.base.RequestCallback

class AddPresenter(
    private var view: Add.View?,
    private val repository: AddRepository
) : Add.Presenter {

    override fun createPost(uri: Uri, caption: String) {
        view?.showProgress(true)
        repository.createPost(uri, caption, object : RequestCallback<Boolean> {
            override fun onSuccess(data: Boolean) {
                if (data) {
                    view?.displayRequestSuccess()
                } else {
                    view?.displayRequestFailure("Erro ao criar o post")
                }
            }

            override fun onFailure(msg: String) {
                view?.displayRequestFailure(msg)
            }

            override fun onComplete() {
                view?.showProgress(false)
            }

        })
    }
    override fun onDestroy() {
        view = null
    }
}