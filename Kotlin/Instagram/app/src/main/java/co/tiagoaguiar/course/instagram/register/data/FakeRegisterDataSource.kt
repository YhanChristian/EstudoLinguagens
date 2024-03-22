package co.tiagoaguiar.course.instagram.register.data

import android.net.Uri
import android.os.Handler
import android.os.Looper
import co.tiagoaguiar.course.instagram.commom.model.Database
import co.tiagoaguiar.course.instagram.commom.model.Photo
import co.tiagoaguiar.course.instagram.commom.model.UserAuth
import java.util.UUID

class FakeRegisterDataSource : RegisterDataSource {
    override fun create(email: String, callback: RegisterCallback) {
        Handler(Looper.getMainLooper()).postDelayed({
            when (Database.usersAuth.firstOrNull { it.email == email }) {
                null -> callback.onSuccess()
                else -> callback.onFailure("Usuário já cadastrado!")
            }
            callback.onComplete()
        }, 500)
    }

    override fun create(email: String, name: String, password: String, callback: RegisterCallback) {
        Handler(Looper.getMainLooper()).postDelayed({
            when (Database.usersAuth.firstOrNull { it.email == email }) {
                null -> {
                    val newUser = UserAuth(UUID.randomUUID().toString(), name, email, password)
                    when (Database.usersAuth.add(newUser)) {
                        true -> {
                            Database.sessionAuth = newUser
                            callback.onSuccess()
                        }
                        else -> callback.onFailure("Erro ao criar o usuário!")
                    }
                }
                else -> callback.onFailure("Usuário já cadastrado!")
            }
            callback.onComplete()
        }, 500)
    }

    override fun updateUser(photoUri: Uri, callback: RegisterCallback) {
        Handler(Looper.getMainLooper()).postDelayed({
            val userAuth = Database.sessionAuth
            if (userAuth != null) {
                val newPhoto = Photo(userAuth.uuid, photoUri)
                when (Database.photos.add(newPhoto)) {
                    true ->  callback.onSuccess()
                    else -> callback.onFailure("Erro ao atualizar perfil")
                }
            } else {
                callback.onFailure("Usuário não encontrado")
            }
            callback.onComplete()
        }, 1000)
    }
}