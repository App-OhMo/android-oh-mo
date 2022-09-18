package oh.mo.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import oh.mo.databinding.ItemPhotoBigBinding
import oh.mo.databinding.ItemPhotoSmallBinding
import oh.mo.utils.VHBindingByType

class PhotoRcvAdapter(private val type: VHBindingByType) : ListAdapter<Int, PhotoRcvAdapter.PhotoViewHolder>(PhotoDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return when(type) {
            VHBindingByType.TODAY_WEATHER -> {
                PhotoViewHolder(ItemPhotoSmallBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ))
            }
            VHBindingByType.POST -> {
                return PhotoViewHolder(ItemPhotoBigBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ))
            }
        }
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    open class PhotoViewHolder(binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        open fun bind(imageSource: Int) { }
    }

    class SmallPhotoViewHolder(private val binding: ItemPhotoSmallBinding) : PhotoViewHolder(binding) {
        override fun bind(imageSource: Int) {
            binding.ivRcvPhoto.setImageResource(imageSource)
        }
    }

    class BigPhotoViewHolder(private val binding: ItemPhotoBigBinding) : PhotoViewHolder(binding) {
        override fun bind(imageSource: Int) {
            binding.ivRcvPhoto.setImageResource(imageSource)
        }
    }


    private class PhotoDiffUtil : DiffUtil.ItemCallback<Int>() {
        override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean =
            oldItem == newItem
    }

}