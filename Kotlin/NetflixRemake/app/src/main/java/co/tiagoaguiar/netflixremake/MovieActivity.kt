package co.tiagoaguiar.netflixremake

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.LayerDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.tiagoaguiar.netflixremake.model.Movie

class MovieActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

        val txtTitle : TextView = findViewById(R.id.movie_txt_title)
        val txtDesc : TextView = findViewById(R.id.movie_txt_desc)
        val castDesc : TextView = findViewById(R.id.movie_txt_cast)
        val similar : TextView = findViewById(R.id.movie_txt_similar)
        val rvSimilar : RecyclerView = findViewById(R.id.movie_rv_similar)

        txtTitle.text = "Batman Begins"
        txtDesc.setText(R.string.hello_desc)
        castDesc.text = getString(R.string.cast, "Christian Bale, Michael Caine, Liam Neeson, Katie Holmes, Gary Oldman, Morgan Freeman")
        similar.setText(R.string.similar)

        val movies = mutableListOf<Movie>()
        for(i in 0 until 10) {
            val movie = Movie(R.drawable.movie)
            movies.add(movie)
        }

        rvSimilar.layoutManager = GridLayoutManager(this, 3)
        rvSimilar.adapter = MovieAdapter(movies, R.layout.movie_item_similar)

        val toolbar : Toolbar = findViewById(R.id.movie_toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        /*
         * Cria desenhável e atribui a imagem de fundo (filme)
         */
        val layerDrawable : LayerDrawable = ContextCompat.getDrawable(this, R.drawable.shadows) as LayerDrawable
        val movieCover = ContextCompat.getDrawable(this, R.drawable.movie_4)
        layerDrawable.setDrawableByLayerId(R.id.cover_drawable, movieCover)
        val coverImg : ImageView = findViewById(R.id.movie_img)
        coverImg.setImageDrawable(layerDrawable)
    }
}