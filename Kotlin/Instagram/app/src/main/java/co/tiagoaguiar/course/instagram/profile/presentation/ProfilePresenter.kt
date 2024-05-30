package co.tiagoaguiar.course.instagram.profile.presentation

import co.tiagoaguiar.course.instagram.commom.base.RequestCallback
import co.tiagoaguiar.course.instagram.commom.model.Database
import co.tiagoaguiar.course.instagram.commom.model.Post
import co.tiagoaguiar.course.instagram.commom.model.UserAuth
import co.tiagoaguiar.course.instagram.profile.Profile
import co.tiagoaguiar.course.instagram.profile.data.ProfileRepository
import java.lang.RuntimeException

class ProfilePresenter(
    private var view: Profile.View?,
    private val repository: ProfileRepository) : Profile.Presenter {
    override fun fetchUserProfile(uuid: String?) {
        view?.showProgress(true)
        repository.fetchUserProfile(uuid, object : RequestCallback<UserAuth>{
            override fun onSuccess(data: UserAuth) {
                view?.displayUserProfile(data)
            }

            override fun onFailure(msg: String) {
                view?.displayRequestFailure(msg)
            }
            override fun onComplete() {
                /*Nada a implementar*/
            }

        })
    }

    override fun fetchUserPosts(uuid: String?) {
        repository.fetchUserPosts(uuid, object : RequestCallback<List<Post>>{
            override fun onSuccess(data: List<Post>) {
                if(data.isEmpty()){
                    view?.displayEmptyList()
                } else {
                    view?.displayUserPosts(data)
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

    override fun clear() {
        repository.clearCache()
    }

    override fun onDestroy() {
        view = null
    }
}