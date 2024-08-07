package co.tiagoaguiar.course.instagram.login.data

import android.os.Handler
import android.os.Looper
import co.tiagoaguiar.course.instagram.commom.model.Database

class FakeDataSource : LoginDataSource {

    override fun login(email: String, password: String, callback: LoginCallback) {
        Handler(Looper.getMainLooper()).postDelayed({
           val userAuth = Database.usersAuth.firstOrNull{ it.email == email }
            when {
                userAuth == null -> {
                    callback.onFailure("Usuário não encontrado!")
                }
                userAuth.password != password -> {
                    callback.onFailure("Senha inválida!")
                }
                else -> {
                    callback.onSuccess()
                }
            }
            callback.onComplete()
        }, 2000)
    }

}