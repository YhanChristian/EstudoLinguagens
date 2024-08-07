package co.tiagoaguiar.course.instagram.login.data

import com.google.firebase.auth.FirebaseAuth

class FireDataSource : LoginDataSource {
    override fun login(email: String, password: String, callback: LoginCallback) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnSuccessListener { res ->
                if(res.user != null) {
                    callback.onSuccess()
                } else {
                    callback.onFailure("Erro ao fazer login")
                }
            }
            .addOnFailureListener { exception ->
                callback.onFailure(exception.message ?: "Erro ao fazer login")
            }
            .addOnCompleteListener {
                callback.onComplete()
            }
    }
}