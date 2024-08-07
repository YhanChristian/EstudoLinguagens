package co.tiagoaguiar.course.instagram.profile.data

import android.widget.Toast
import co.tiagoaguiar.course.instagram.commom.base.RequestCallback
import co.tiagoaguiar.course.instagram.commom.model.Post
import co.tiagoaguiar.course.instagram.commom.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.Query

class ProfileFireDataSource : ProfileDataSource {
    override fun fetchUserProfile(
        userUUID: String,
        callback: RequestCallback<Pair<User, Boolean?>>
    ) {
        FirebaseFirestore.getInstance()
            .collection("/users")
            .document(userUUID)
            .get()
            .addOnSuccessListener { res ->
                val user = res.toObject(User::class.java)
                when (user) {
                    null -> callback.onFailure("Usuario não encontrado")
                    else -> {
                        if (user.uuid == FirebaseAuth.getInstance().uid) {
                            callback.onSuccess(Pair(user, null))
                        } else {
                            FirebaseFirestore.getInstance()
                                .collection("/followers")
                                .document(userUUID)
                                .get()
                                .addOnSuccessListener { response ->
                                    if(!response.exists()) {
                                        callback.onSuccess(Pair(user, false))
                                    } else {
                                        val list = response.get("followers") as List<*>
                                        callback.onSuccess(Pair(user, list.contains(FirebaseAuth.getInstance().uid)))
                                    }
                                }
                                .addOnFailureListener { exception ->
                                    callback.onFailure(
                                        exception.message ?: "Erro ao buscar seguidores"
                                    )
                                }
                                .addOnCompleteListener {
                                    callback.onComplete()
                                }
                        }
                    }
                }
            }
            .addOnFailureListener { exception ->
                callback.onFailure(exception.message ?: "Erro ao carregar ao buscar usuario")
            }
    }

    override fun fetchUserPosts(userUUID: String, callback: RequestCallback<List<Post>>) {
        FirebaseFirestore.getInstance()
            .collection("/posts")
            .document(userUUID)
            .collection("posts")
            .orderBy("timestamp", Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener { res ->
                val documents = res.documents
                val posts = mutableListOf<Post>()
                for (document in documents) {
                    val post = document.toObject(Post::class.java)
                    if (post != null) {
                        posts.add(post)
                    }
                }
                callback.onSuccess(posts)
            }
            .addOnFailureListener { exception ->
                callback.onFailure(exception.message ?: "Erro ao carregar os posts")
            }
            .addOnCompleteListener() {
                callback.onComplete()
            }

    }

    override fun followUser(
        userUUID: String,
        isFollow: Boolean,
        callback: RequestCallback<Boolean>
    ) {
        FirebaseFirestore.getInstance()
        val uid = FirebaseAuth.getInstance().uid ?: throw RuntimeException("Usuario não logado")
        FirebaseFirestore.getInstance()
            .collection("/followers")
            .document(userUUID)
            .update("followers", if (isFollow) FieldValue.arrayUnion(uid)
            else FieldValue.arrayRemove(uid))
            .addOnSuccessListener { res ->
                followingCounter(uid, isFollow)
                followersCounter(userUUID, callback)
                updateFeed(userUUID, isFollow)
            }
            .addOnFailureListener { exception ->
                val err = exception as? FirebaseFirestoreException
                if(err?.code == FirebaseFirestoreException.Code.NOT_FOUND) {
                    FirebaseFirestore.getInstance()
                        .collection("/followers")
                        .document(userUUID)
                        .set(hashMapOf("followers" to listOf(uid)))
                        .addOnSuccessListener { res ->
                            followingCounter(uid, isFollow)
                            followersCounter(userUUID, callback)
                            updateFeed(userUUID, isFollow)
                        }
                        .addOnFailureListener { exc ->
                            callback.onFailure(exc.message ?: "Erro ao criar o documento seguidor!")
                        }
                }
                callback.onFailure(exception.message ?: "Erro ao seguir usuario")
            }
            .addOnCompleteListener {
                callback.onComplete()
            }
    }

    private fun followingCounter(uid: String, isFollow: Boolean) {
        val meRef = FirebaseFirestore.getInstance()
            .collection("/users")
            .document(uid)
        if(isFollow) meRef.update("following", FieldValue.increment(1))
        else meRef.update("following", FieldValue.increment(-1))
    }

    private fun followersCounter(userUUID: String, callback: RequestCallback<Boolean>) {
        val meRef = FirebaseFirestore.getInstance()
            .collection("/users")
            .document(userUUID)

        FirebaseFirestore.getInstance()
            .collection("/followers")
            .document(userUUID)
            .get()
            .addOnSuccessListener { res ->
                val list = res.get("followers") as List<*>
                meRef.update("followers", list.size)
            }
        callback.onSuccess(true)
    }

    private fun updateFeed(userUUID: String, isFollow: Boolean) {
        if(!isFollow) {
           /*Remove from feed */
            FirebaseFirestore.getInstance()
                .collection("/feeds")
                .document(FirebaseAuth.getInstance().uid!!)
                .collection("posts")
                .whereEqualTo("publisher.uuid", userUUID)
                .get()
                .addOnSuccessListener { res ->
                    val documents = res.documents
                    for(document in documents) {
                        document.reference.delete()
                    }
                }
        } else {
            /*Add to feed*/
            FirebaseFirestore.getInstance()
                .collection("/posts")
                .document(userUUID)
                .collection("posts")
                .get()
                .addOnSuccessListener { res ->
                    val posts = res.toObjects(Post::class.java)
                    posts.lastOrNull()?.let {
                        FirebaseFirestore.getInstance()
                            .collection("/feeds")
                            .document(FirebaseAuth.getInstance().uid!!)
                            .collection("posts")
                            .document(it.uuid!!)
                            .set(it)
                    }
                }
        }
    }
}