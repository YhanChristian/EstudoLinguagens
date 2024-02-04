package co.tiagoaguiar.netflixremake

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.LayerDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.tiagoaguiar.netflixremake.model.Category
import co.tiagoaguiar.netflixremake.model.Movie
import co.tiagoaguiar.netflixremake.model.MovieDetail
import co.tiagoaguiar.netflixremake.util.DownloadImageTask
import co.tiagoaguiar.netflixremake.util.MovieTask

class MovieActivity : AppCompatActivity(), MovieTask.Callback {

    private lateinit var  txtTitle : TextView
    private lateinit var  txtDesc : TextView
    private lateinit var  castDesc : TextView
    private lateinit var  similar : TextView
    private lateinit var  adapter: MovieAdapter
    private lateinit var pb : ProgressBar
    private val movies = mutableListOf<Movie>()
    companion object {
        const val TAG = "MovieActivity"
    }
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

        txtTitle = findViewById(R.id.movie_txt_title)
        txtDesc = findViewById(R.id.movie_txt_desc)
        castDesc = findViewById(R.id.movie_txt_cast)
        similar = findViewById(R.id.movie_txt_similar)
        val rvSimilar : RecyclerView = findViewById(R.id.movie_rv_similar)
        pb = findViewById(R.id.pb_movie_detail)

        val id = intent?.getIntExtra("id", 0) ?: throw IllegalStateException("Id não encontrado")
        val url = "https://api.tiagoaguiar.co/netflixapp/movie/$id?apiKey=YOUR_API_KEY"

        MovieTask(this).execute(url)

        txtTitle.text = "Batman Begins"
        txtDesc.setText(R.string.hello_desc)
        castDesc.text = getString(R.string.cast, "Christian Bale, Michael Caine, Liam Neeson, Katie Holmes, Gary Oldman, Morgan Freeman")
        similar.setText(R.string.similar)


        rvSimilar.layoutManager = GridLayoutManager(this, 3)
        adapter = MovieAdapter(movies, R.layout.movie_item_similar)
        rvSimilar.adapter = adapter

        val toolbar : Toolbar = findViewById(R.id.movie_toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        /*
         * Cria desenhável e atribui a imagem de fundo (filme)
         */
        val layerDrawable : LayerDrawable = ContextCompat.getDrawable(this, R.drawable.shadows) as LayerDrawable
        val movieCover = ContextCompat.getDrawable(this, R.drawable.placeholder_bg)
        layerDrawable.setDrawableByLayerId(R.id.cover_drawable, movieCover)
        val coverImg : ImageView = findViewById(R.id.movie_img)
        coverImg.setImageDrawable(layerDrawable)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onPreExecute() {
        pb.visibility = ProgressBar.VISIBLE
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onResult(movieDetail: MovieDetail) {
        Log.d(TAG, "[onResult] $movieDetail")
        pb.visibility = ProgressBar.GONE
        txtTitle.text = movieDetail.movie.title
        txtDesc.text = movieDetail.movie.desc
        castDesc.text = getString(R.string.cast, movieDetail.movie.cast)
        movies.clear()
        movies.addAll(movieDetail.similars)
        adapter.notifyDataSetChanged()
        DownloadImageTask(object : DownloadImageTask.Callback {
            override fun onResult(bitmap: Bitmap) {
                val layerDrawable : LayerDrawable = ContextCompat.getDrawable(this@MovieActivity, R.drawable.shadows) as LayerDrawable
                val movieCover = BitmapDrawable(resources, bitmap)
                layerDrawable.setDrawableByLayerId(R.id.cover_drawable, movieCover)
                val coverImg : ImageView = findViewById(R.id.movie_img)
                coverImg.setImageDrawable(layerDrawable)
            }
        }).execute(movieDetail.movie.coverUrl)
    }

    override fun onFailure(message: String) {
        Log.d(TAG, "[onFailure] $message")
        pb.visibility = ProgressBar.GONE
    }
}