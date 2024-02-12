package co.tiagoaguiar.tutorial.jokerappdev.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import co.tiagoaguiar.tutorial.jokerappdev.R
import co.tiagoaguiar.tutorial.jokerappdev.model.Joke
import co.tiagoaguiar.tutorial.jokerappdev.presentation.JokePresenter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupieAdapter

class JokeFragment : Fragment() {
    companion object {
        const val CATEGORY_KEY = "category"
        private const val TAG = "JokeFragment"
    }

    private lateinit var progressBar: ProgressBar
    private lateinit var presenter: JokePresenter
    private lateinit var textView: TextView
    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = JokePresenter(this)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_joke, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val categoryName = arguments?.getString(CATEGORY_KEY)
        Log.d(TAG, "onViewCreated category: $categoryName")
        activity?.findViewById<Toolbar>(R.id.toolbar)?.title = categoryName
        progressBar = view.findViewById(R.id.pb_joke)
        textView = view.findViewById(R.id.txt_joke)
        imageView = view.findViewById(R.id.img_joke)

        view.findViewById<FloatingActionButton>(R.id.fab_joke).setOnClickListener {
            categoryName?.let {
                presenter.findByCategoryName(categoryName)
            }
        }

        categoryName?.let {
            presenter.findByCategoryName(categoryName)
        }
    }

    fun showJoke(joke: Joke) {
        textView.text = joke.text
        Picasso.get().load(joke.iconURL).into(imageView)
    }

    fun showError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }

}