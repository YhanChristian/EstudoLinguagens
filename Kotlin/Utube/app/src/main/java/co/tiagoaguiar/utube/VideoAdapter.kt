package co.tiagoaguiar.utube

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_video.view.*

/**
 *
 * Setembro, 10 2019
 * @author suporte@moonjava.com.br (Tiago Aguiar).
 */
class VideoAdapter(private val videos: List<Video>, val onClick: (Video) -> Unit) : RecyclerView.Adapter<VideoAdapter.VideoHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoHolder =
        VideoHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_item_video,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = videos.size

    override fun onBindViewHolder(holder: VideoHolder, position: Int) {
        holder.bind(videos[position])
    }

    inner class VideoHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(video: Video) {
            with(itemView) {
                setOnClickListener {
                    onClick.invoke(video)
                }

                Picasso.get().load(video.thumbnailUrl).into(video_thumbnail)
                Picasso.get().load(video.publisher.pictureProfileUrl).into(video_author)
                video_title.text = video.title
                video_info.text = context.getString(R.string.info,
                    video.publisher.name, video.viewsCountLabel, video.publishedAt.formatted())
            }
        }
    }
}