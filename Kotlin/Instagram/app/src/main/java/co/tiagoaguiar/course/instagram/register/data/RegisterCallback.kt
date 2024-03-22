package co.tiagoaguiar.course.instagram.register.data

interface RegisterCallback {
    fun onSuccess()
    fun onFailure(msg: String)
    fun onComplete()
}