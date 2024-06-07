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
        repository.fetchUserProfile(uuid, object : RequestCallback<Pair<UserAuth, Boolean?>>{
            override fun onSuccess(data: Pair<UserAuth, Boolean?>) {
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

    override fun followUser(uuid: String?, follow: Boolean) {
        repository.followUser(uuid, follow, object : RequestCallback<Boolean>{
            override fun onSuccess(data: Boolean) {
            }
            override fun onFailure(msg: String) {
            }
            override fun onComplete() {
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