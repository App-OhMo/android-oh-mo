package oh.mo.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import oh.mo.databinding.ItemRecommendationsFeedBinding

class RecommendationsFeedAdapter :
    RecyclerView.Adapter<RecommendationsFeedAdapter.RecommendationsFeedViewHolder>() {

    private val diffUtilCallback = object : DiffUtil.ItemCallback<String>() {

        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffUtilCallback)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RecommendationsFeedViewHolder =
        RecommendationsFeedViewHolder(ItemRecommendationsFeedBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: RecommendationsFeedViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int = differ.currentList.size

    inner class RecommendationsFeedViewHolder(private val binding: ItemRecommendationsFeedBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            binding.tvRecommendationsFeed.text = differ.currentList[bindingAdapterPosition]
        }
    }
}