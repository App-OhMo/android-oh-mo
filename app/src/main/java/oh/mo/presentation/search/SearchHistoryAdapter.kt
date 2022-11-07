package oh.mo.presentation.search

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.qualifiers.ApplicationContext
import oh.mo.databinding.ItemSearchKeywordBinding
import okhttp3.internal.notify

class SearchHistoryAdapter(private val kList: ArrayList<String>): RecyclerView.Adapter<SearchHistoryAdapter.ViewHolder>() {

    private lateinit var itemClickListener: HistoryItemClickListener

    fun setHistoryClickListener(itemClickListener: HistoryItemClickListener){
        this.itemClickListener = itemClickListener
    }

    interface HistoryItemClickListener{
        fun onItemClick(keyword: String)

        fun onRemoveItemClick(position: Int)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemSearchKeywordBinding = ItemSearchKeywordBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(kList[position])
        holder.itemView.setOnClickListener{
            itemClickListener.onItemClick(kList[position])
        }
        holder.binding.tvCurrentKeywordEmpty.setOnClickListener{
            itemClickListener.onRemoveItemClick(position)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int = kList.size

    inner class ViewHolder(val binding: ItemSearchKeywordBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(keyword:String){
            binding.tvCurrentKeyword.text = keyword
        }
    }
}