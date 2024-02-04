package co.tiagoaguiar.netflixremake.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Handler
import android.os.Looper
import android.util.Log
import java.io.IOException
import java.io.InputStream
import java.net.URL
import java.util.concurrent.Executors
import javax.net.ssl.HttpsURLConnection

class DownloadImageTask(private val callback: Callback) {
    private val handler = Handler(Looper.getMainLooper())

    interface Callback {
        fun onResult(bitmap: Bitmap)
    }
    fun execute(url : String) {
        val executor = Executors.newSingleThreadExecutor()
        executor.execute {
            var connection: HttpsURLConnection? = null
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
                val bitmap = BitmapFactory.decodeStream(stream)
                handler.post() {
                    callback.onResult(bitmap)
                }
            } catch (e: IOException) {
                val message = e.message ?: "Erro desconhecido"
                Log.e(CategoryTask.TAG, "Error on request: $message")
            } finally {
                connection?.disconnect()
                stream?.close()
            }
        }
    }
}