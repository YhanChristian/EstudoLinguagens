package co.tiagoaguiar.netflixremake.util

import android.os.Handler
import android.os.Looper
import android.util.Log
import co.tiagoaguiar.netflixremake.model.Category
import co.tiagoaguiar.netflixremake.model.Movie
import co.tiagoaguiar.netflixremake.model.MovieDetail
import org.json.JSONObject
import java.io.BufferedInputStream
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import java.net.URL
import java.util.concurrent.Executors
import javax.net.ssl.HttpsURLConnection

class CategoryTask(private val callback: Callback) {

    private val handler = Handler(Looper.getMainLooper())
    companion object {
        const val TAG = "CategoryTask"
    }
    interface Callback {

        fun onPreExecute()

        fun onResult(categories: List<Category>)
        fun onFailure(message: String)
    }

    fun execute(url: String) {
        callback.onPreExecute()
        val executor = Executors.newSingleThreadExecutor()
        executor.execute() {
            var connection: HttpsURLConnection? = null
            var buffer: BufferedInputStream? = null
            var stream: InputStream? = null
            try {
                val requestURL = URL(url)
                connection = requestURL.openConnection() as HttpsURLConnection
                connection.readTimeout = Constants.TIMEOUT
                connection.connectTimeout =  Constants.TIMEOUT
                val status = connection.responseCode
                if (status > Constants.BAD_REQUEST) {
                    throw IOException("Erro na comunicação com o servidor. Codigo: $status")
                }
                stream = connection.inputStream
                buffer = BufferedInputStream(stream)
                val jsonToString = toString(buffer)
                val categories = toCategories(jsonToString)

                handler.post() {
                    callback.onResult(categories)
                }

            } catch (e: IOException) {
                val message = e.message ?: "Erro desconhecido"
                Log.e(TAG, "Error on request: $message")
                handler.post() {
                    callback.onFailure(message)
                }
            } finally {
                connection?.disconnect()
                stream?.close()
                buffer?.close()
            }
        }
    }

    private fun toCategories(json: String): List<Category> {
        val categories = mutableListOf<Category>()
        val jsonRoot = JSONObject(json)
        val jsonCategories = jsonRoot.getJSONArray("category")

        for (i in 0 until jsonCategories.length()) {
            val jsonCategory = jsonCategories.getJSONObject(i)
            val title = jsonCategory.getString("title")
            val movies = mutableListOf<Movie>()
            val jsonMovies = jsonCategory.getJSONArray("movie")

            for (j in 0 until jsonMovies.length()) {
                val jsonMovie = jsonMovies.getJSONObject(j)
                val id = jsonMovie.getInt("id")
                val coverUrl = jsonMovie.getString("cover_url")
                val movie = Movie(id, coverUrl)
                movies.add(movie)
            }

            val category = Category(title, movies)
            categories.add(category)
        }

        return categories
    }

    private fun toString(stream: InputStream): String {
        val content = ByteArray(1024)
        val output = ByteArrayOutputStream()
        var read: Int
        while (true) {
            read = stream.read(content)
            if (read <= 0) {
                break
            }
            output.write(content, 0, read)
        }
        return String(output.toByteArray())
    }
}
