package co.tiagoaguiar.course.instagram.register.presentation

import co.tiagoaguiar.course.instagram.R
import co.tiagoaguiar.course.instagram.register.RegisterNamePassword
import co.tiagoaguiar.course.instagram.register.data.RegisterCallback
import co.tiagoaguiar.course.instagram.register.data.RegisterRepository

class RegisterNamePasswordPresenter(
    private var view: RegisterNamePassword.View?,
    private val repository: RegisterRepository
) : RegisterNamePassword.Presenter {
    override fun create(email: String, name: String, password: String, confirm: String) {
        val isValidName = name.length > 3

        if (!isValidName) {
            view?.displayNameFailure(R.string.name_must_be_longer)
            return
        }
        if (password.length < 8) {
            view?.displayPasswordFailure(R.string.invalid_password)
            return
        }
        if (password != confirm) {
            view?.displayPasswordFailure(R.string.passwords_do_not_match)
            return
        }

        repository.create(email, name, password, object : RegisterCallback {
            override fun onSuccess() {
                view?.onCreateSuccess(name)
            }

            override fun onFailure(msg: String) {
                view?.onCreateFailure(msg)
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