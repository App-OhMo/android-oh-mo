package oh.mo.presentation.search

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import oh.mo.databinding.FragmentSearchBinding
import oh.mo.presentation.base.BaseFragment
import oh.mo.utils.SharedPrefUtil
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.collections.ArrayList

class SearchFragment: BaseFragment<FragmentSearchBinding>() {

    private val currentTime = LocalDateTime.now()
    private val currentDate = currentTime.format(DateTimeFormatter.ofPattern("(MM.dd HH:mm)"))
    private lateinit var historyAdapter: SearchHistoryAdapter
    private var keywords = ArrayList<String>()
    private val viewModel: SearchViewModel by viewModels()

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSearchBinding {
        return FragmentSearchBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initRecyclerView()
        setKeywordEnter()
    }

    private fun setKeywordEnter(){
        binding.etSearchKeyword.setOnKeyListener{v, keyCode, event ->
            if(event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER){
                var kList = ArrayList<String>()
                for(k in keywords)
                    kList.add(k)
                kList.add(binding.etSearchKeyword.text.toString())

                SharedPrefUtil.setSearchHistoryList(kList)
                binding.etSearchKeyword.clearFocus()
            }
            return@setOnKeyListener false
        }
    }

    private fun initRecyclerView(){
        keywords = SharedPrefUtil.getSearchHistoryList() as ArrayList<String>
        if(keywords.size == 0 || keywords.isEmpty()){
            //dummy data
            keywords.apply {
                add("자켓")
                add("목도리")
            }
        }
        keywords.forEach{
            Log.d("SearchFragment", "저장기록: ${it}")
        }

        historyAdapter = SearchHistoryAdapter(keywords)
        binding.rvSearchCurrentKeyword.adapter = historyAdapter
        binding.rvSearchCurrentKeyword.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        historyAdapter.setHistoryClickListener(object : SearchHistoryAdapter.HistoryItemClickListener{
            override fun onItemClick(keyword: String) {
                Toast.makeText(context, "아이템 클릭", Toast.LENGTH_SHORT).show()
            }

            override fun onRemoveItemClick(position: Int) {
                Toast.makeText(context, "검색어 삭제", Toast.LENGTH_SHORT).show()
                keywords.remove(keywords[position])
                setkeywordSharedPref()
            }
        })
    }

    private fun initView(){
        binding.tvPopularCategoryDate.text = currentDate

//        binding.btnSearchTopCancel.setOnClickListener{
//            val onBackPressedCallback = object : OnBackPressedCallback(true) {
//                override fun handleOnBackPressed() {
//                    Toast.makeText(context, "취소", Toast.LENGTH_SHORT).show()
//                }
//            }
//        }
    }

    private fun setkeywordSharedPref(){
        var kList = ArrayList<String>()
        for(k in keywords)
            kList.add(k)

        SharedPrefUtil.setSearchHistoryList(kList)
    }

}