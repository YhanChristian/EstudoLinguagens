package co.tiagoaguiar.course.instagram.home.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.tiagoaguiar.course.instagram.R
import co.tiagoaguiar.course.instagram.commom.base.BaseFragment
import co.tiagoaguiar.course.instagram.databinding.FragmentHomeBinding
import co.tiagoaguiar.course.instagram.home.Home

class HomeFragment : BaseFragment<FragmentHomeBinding, Home.Presenter>
    (R.layout.fragment_home, FragmentHomeBinding::bind) {

    override lateinit var presenter: Home.Presenter
    override fun setupViews() {
        binding?.rvHome?.layoutManager = LinearLayoutManager(requireContext())
        binding?.rvHome?.adapter = PostAdapter()
    }

    override fun setupPresenter() {
        //TODO("Not yet implemented")
    }

    override fun getMenu(): Int {
        return R.menu.menu_profile
    }

    private class PostAdapter : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
            return PostViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_post_list, parent, false))
        }

        override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
            holder.bind(R.drawable.ic_insta_add)
        }

        override fun getItemCount(): Int {
            return 30
        }

        private class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            fun bind(@DrawableRes image: Int) {
                itemView.findViewById<ImageView>(R.id.img_home_post).setImageResource(image)
            }
        }
    }
}