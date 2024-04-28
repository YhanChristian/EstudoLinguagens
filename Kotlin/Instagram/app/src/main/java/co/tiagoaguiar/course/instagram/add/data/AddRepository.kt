package co.tiagoaguiar.course.instagram.add.data

import android.net.Uri
import co.tiagoaguiar.course.instagram.commom.base.RequestCallback

class AddRepository(private val remoteDataSource: AddFakeRemoteDataSource,
    private val localDataSource: AddLocalDataSource)  {
    fun createPost(uri: Uri, caption: String, callback: RequestCallback<Boolean>) {
        val userAuth = localDataSource.fetchSession()
        remoteDataSource.createPost(userAuth.uuid, uri, caption, object: RequestCallback<Boolean>{
            override fun onSuccess(data: Boolean) {
                callback.onSuccess(data)
            }

            override fun onFailure(msg: String) {
                callback.onFailure(msg)
            }

            override fun onComplete() {
                callback.onComplete()
            }
        })
    }
}