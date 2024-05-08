package co.tiagoaguiar.course.instagram.post.presentation

import android.net.Uri
import co.tiagoaguiar.course.instagram.post.Post
import co.tiagoaguiar.course.instagram.post.data.PostRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext


class PostPresenter(
    private var view : Post.View?,
    private val repository: PostRepository
) : Post.Presenter, CoroutineScope {

    private val job = Job()
    override val coroutineContext : CoroutineContext = job + Dispatchers.IO
    private var _uri : Uri? = null
    override fun fetchPictures() {
        view?.showProgress(true)
        launch {
           val pictures = repository.fetchPictures()
            withContext(Dispatchers.Main) {
                if(pictures.isEmpty()) view?.displayEmptyPictures()
                else view?.displayFullPictures(pictures)
                view?.showProgress(false)
            }

        }

    }
    override fun selectPicture(uri: Uri) {
        _uri = uri
    }
    override fun getSelectedPicture(): Uri? {
        return _uri
    }
    override fun onDestroy() {
        job.cancel()
        view = null
    }

}