package co.tiagoaguiar.course.instagram.add.data

import android.net.Uri
import co.tiagoaguiar.course.instagram.commom.base.RequestCallback

class AddRepository(private val remoteDataSource: FireAddDataSource,
    private val localDataSource: AddLocalDataSource)  {
    fun createPost(uri: Uri, caption: String, callback: RequestCallback<Boolean>) {
        val uuid = localDataSource.fetchSession()
        remoteDataSource.createPost(uuid, uri, caption, object: RequestCallback<Boolean>{
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