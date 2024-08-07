package co.tiagoaguiar.course.instagram.home.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import co.tiagoaguiar.course.instagram.R
import co.tiagoaguiar.course.instagram.commom.model.Post
import com.bumptech.glide.Glide

class FeedAdapter : RecyclerView.Adapter<FeedAdapter.FeedViewHolder>() {

    var items : List<Post> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        return FeedViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_post_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class FeedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(post: Post) {
            Glide.with(itemView.context).load(post.photoUrl).into(itemView.findViewById(R.id.img_home_post))
            Glide.with(itemView.context).load(post.publisher?.photoUrl).into(itemView.findViewById(R.id.img_home_user))
            itemView.findViewById<TextView>(R.id.txt_home_caption).text = post.caption
            itemView.findViewById<TextView>(R.id.txt_home_user).text = post.publisher?.name
        }
    }
}