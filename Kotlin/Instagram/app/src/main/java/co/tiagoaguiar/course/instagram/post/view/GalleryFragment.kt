package co.tiagoaguiar.course.instagram.post.view

import android.net.Uri
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.recyclerview.widget.GridLayoutManager
import co.tiagoaguiar.course.instagram.R
import co.tiagoaguiar.course.instagram.commom.base.BaseFragment
import co.tiagoaguiar.course.instagram.commom.base.DependencyInjector
import co.tiagoaguiar.course.instagram.databinding.FragmentGalleryBinding
import co.tiagoaguiar.course.instagram.post.Post
import co.tiagoaguiar.course.instagram.post.presentation.PostPresenter


class GalleryFragment : BaseFragment<FragmentGalleryBinding, Post.Presenter>(
    R.layout.fragment_gallery,
    FragmentGalleryBinding::bind
), Post.View {

    override lateinit var presenter: Post.Presenter
    private val adapter = PictureAdapter() {
        binding?.imgGallerySelected?.setImageURI(it)
        binding?.nestedGallery?.smoothScrollTo(0, 0)
        presenter.selectPicture(it)
    }
    override fun setupViews() {
        binding?.rvGallery?.layoutManager = GridLayoutManager(requireContext(), 3)
        binding?.rvGallery?.adapter = adapter
        presenter.fetchPictures()
    }

    override fun setupPresenter() {
        presenter = PostPresenter(this, DependencyInjector.postRepository(requireContext()))
    }
    override fun getMenu(): Int  = R.menu.menu_send
    override fun showProgress(enabled: Boolean) {
        binding?.progressGallery?.visibility = if (enabled) View.VISIBLE else View.GONE
    }

    override fun displayFullPictures(pictures: List<Uri>) {
        binding?.textGalleryEmpty?.visibility = View.GONE
        binding?.rvGallery?.visibility = View.VISIBLE
        adapter.items = pictures
        adapter.notifyDataSetChanged()
        binding?.imgGallerySelected?.setImageURI(pictures.first())
        binding?.nestedGallery?.smoothScrollTo(0, 0)
        presenter.selectPicture(pictures.first())
    }

    override fun displayEmptyPictures() {
        binding?.textGalleryEmpty?.visibility = View.VISIBLE
        binding?.rvGallery?.visibility = View.GONE
    }

    override fun displayRequestFailure(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_send -> {
                setFragmentResult(CameraFragment.KEY_TAKE_PHOTO,
                    bundleOf(CameraFragment.KEY_URI to presenter.getSelectedPicture()))
                return true
            }
        }
        return false
    }
}