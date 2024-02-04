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

class MovieTask(private val callback: Callback) {

    private val handler = Handler(Looper.getMainLooper())
    companion object {
        const val TAG = "MovieTask"
    }
    interface Callback {

        fun onPreExecute()
        fun onResult(movieDetail: MovieDetail)
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

                if(status == Constants.BAD_REQUEST) {
                    stream = connection.errorStream
                    buffer = BufferedInputStream(stream)
                    val jsonToString = toString(buffer)

                    val msg = JSONObject(jsonToString).getString("message")
                    throw IOException(msg)

                } else if (status > Constants.BAD_REQUEST) {
                    throw IOException("Erro na comunicação com o servidor. Codigo: $status")
                }

                stream = connection.inputStream
                buffer = BufferedInputStream(stream)

                val jsonToString = toString(buffer)
                val movieDetail = toMovieDetail(jsonToString)

                handler.post() {
                    callback.onResult(movieDetail)
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

    private fun toMovieDetail(json: String): MovieDetail {
        val jsonObject = JSONObject(json)

        val id = jsonObject.getInt("id")
        val title = jsonObject.getString("title")
        val desc = jsonObject.getString("desc")
        val cast = jsonObject.getString("cast")
        val coverUrl = jsonObject.getString("cover_url")
        val jsonMovies = jsonObject.getJSONArray("movie")

        val similars = mutableListOf<Movie>()
        for(i in 0 until jsonMovies.length()) {
            val jsonMovie = jsonMovies.getJSONObject(i)
            val similarId = jsonMovie.getInt("id")
            val similarCoverUrl = jsonMovie.getString("cover_url")
            val m = Movie(similarId, similarCoverUrl)
            similars.add(m)
        }

        val movie = Movie(id, coverUrl, title, desc, cast)
        return MovieDetail(movie, similars)
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
