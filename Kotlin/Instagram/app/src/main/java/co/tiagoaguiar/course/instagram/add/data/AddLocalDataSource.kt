package co.tiagoaguiar.course.instagram.add.data

import android.net.Uri
import co.tiagoaguiar.course.instagram.commom.base.RequestCallback
import co.tiagoaguiar.course.instagram.commom.model.Database
import co.tiagoaguiar.course.instagram.commom.model.UserAuth

class AddLocalDataSource : AddDataSource {

    override fun fetchSession(): UserAuth {
        return Database.sessionAuth ?: throw RuntimeException("Usuário não logado!")
    }
    override fun createPost(
        userUUID: String,
        uri: Uri,
        caption: String,
        callback: RequestCallback<Boolean>
    ) {
        // TODO("Not yet implemented")
    }
}