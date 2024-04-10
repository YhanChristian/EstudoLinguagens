package co.tiagoaguiar.netflixremake

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.tiagoaguiar.netflixremake.model.Category
import co.tiagoaguiar.netflixremake.util.CategoryTask

class MainActivity : AppCompatActivity(), CategoryTask.Callback {
    companion object {
        const val TAG = "MainActivity"
        const val URL = "https://api.tiagoaguiar.co/netflixapp/home?apiKey=YOUR_API_KEY"
    }

    private lateinit var rv : RecyclerView
    private lateinit var pb : ProgressBar
    private lateinit var adapter : CategoryAdapter
    private val categories = mutableListOf<Category>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.acttivity_main)

        adapter = CategoryAdapter(categories) { id->
            val intent = Intent(this@MainActivity, MovieActivity::class.java)
            intent.putExtra("id", id)
            startActivity(intent)
        }
        pb = findViewById(R.id.pb_main)
        rv = findViewById(R.id.rv_main)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapter

       CategoryTask(this).execute(URL)
    }

    override fun onPreExecute() {
        pb.visibility = View.VISIBLE
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onResult(categories: List<Category>) {
        Log.d(TAG, "[onResult]")
        this.categories.clear()
        this.categories.addAll(categories)
        adapter.notifyDataSetChanged()
        pb.visibility = View.GONE
    }

    override fun onFailure(message: String) {
        Log.d(TAG, "[onFailure]: $message")
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        pb.visibility = View.GONE
    }
}


