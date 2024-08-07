package co.tiagoaguiar.course.instagram.add.data

import android.net.Uri
import co.tiagoaguiar.course.instagram.commom.base.RequestCallback
import co.tiagoaguiar.course.instagram.commom.model.Post
import co.tiagoaguiar.course.instagram.commom.model.User
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

class FireAddDataSource : AddDataSource {
    override fun createPost(
        userUUID: String,
        uri: Uri,
        caption: String,
        callback: RequestCallback<Boolean>
    ) {
        val uriLastPath = uri.lastPathSegment ?: throw RuntimeException("Erro ao pegar o uri")
        val imgRef = FirebaseStorage.getInstance().reference
            .child("images/")
            .child(userUUID)
            .child(uriLastPath)

        imgRef.putFile(uri)
            .addOnSuccessListener { res ->
                imgRef.downloadUrl
                    .addOnSuccessListener { resDownload ->
                        val meRef = FirebaseFirestore.getInstance()
                            .collection("/users")
                            .document(userUUID)
                            meRef.get()
                            .addOnSuccessListener { resUser ->
                                val user = resUser.toObject(User::class.java)
                                val postRef = FirebaseFirestore.getInstance()
                                    .collection("/posts")
                                    .document(userUUID)
                                    .collection("posts")
                                    .document()
                                val post = Post(
                                    uuid = postRef.id,
                                    photoUrl = resDownload.toString(),
                                    caption = caption,
                                    timestamp = System.currentTimeMillis(),
                                    publisher = user
                                )
                                postRef.set(post)
                                    .addOnSuccessListener { resPost ->
                                        meRef.update("postCount", FieldValue.increment(1))

                                        /*My feed*/
                                        FirebaseFirestore.getInstance()
                                            .collection("/feeds")
                                            .document(userUUID)
                                            .collection("posts")
                                            .document(postRef.id)
                                            .set(post)

                                            .addOnSuccessListener { resMyFeed ->
                                                /*Followers feed*/
                                                FirebaseFirestore.getInstance()
                                                    .collection("/followers")
                                                    .document(userUUID)
                                                    .get()
                                                    .addOnSuccessListener { resFollowers ->
                                                        if(resFollowers.exists()) {
                                                            val list = resFollowers.get("followers") as List<*>
                                                            for (followerUUID in list) {
                                                                FirebaseFirestore.getInstance()
                                                                    .collection("/feeds")
                                                                    .document(followerUUID.toString())
                                                                    .collection("posts")
                                                                    .document(postRef.id)
                                                                    .set(post)
                                                            }
                                                        }

                                                        callback.onSuccess(true)
                                                    }
                                                    .addOnFailureListener { exception ->
                                                        callback.onFailure(
                                                            exception.message
                                                                ?: "Erro ao buscar meus seguidores"
                                                        )
                                                    }
                                                    .addOnCompleteListener {
                                                        callback.onComplete()
                                                    }
                                            }
                                            .addOnFailureListener { exception ->
                                                callback.onFailure(
                                                    exception.message ?: "Erro ao salvar o post")

                                            }
                                    }
                                    .addOnFailureListener { exception ->
                                        callback.onFailure(
                                            exception.message ?: "Erro ao salvar o post"
                                        )
                                    }
                            }
                            .addOnFailureListener { exeception ->
                                callback.onFailure(exeception.message ?: "Erro ao buscar usuário")
                            }
                    }
                    .addOnFailureListener { exception ->
                        callback.onFailure(
                            exception.message ?: "Erro ao fazer o download da imagem"
                        )
                    }
            }
            .addOnFailureListener { exception ->
                callback.onFailure(exception.message ?: "Erro ao salvar a imagem")
            }

    }
}