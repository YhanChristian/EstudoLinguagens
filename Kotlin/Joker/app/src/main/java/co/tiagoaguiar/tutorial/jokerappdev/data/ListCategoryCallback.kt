package co.tiagoaguiar.tutorial.jokerappdev.data

interface ListCategoryCallback {
    fun onSuccess(response: List<String>)
    fun onError(message: String)
    fun onComplete()
}