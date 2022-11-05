package oh.mo.presentation.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import oh.mo.databinding.ItemSearchKeywordBinding

class SearchHistoryAdapter(private val kList: ArrayList<String>): RecyclerView.Adapter<SearchHistoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemSearchKeywordBinding = ItemSearchKeywordBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(kList[position])
    }

    override fun getItemCount(): Int = kList.size

    inner class ViewHolder(val binding: ItemSearchKeywordBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(keyword:String){
            binding.tvCurrentKeyword.text = keyword
        }
    }
}