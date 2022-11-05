package oh.mo.presentation.search

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import oh.mo.databinding.FragmentSearchBinding
import oh.mo.presentation.base.BaseFragment

class SearchFragment: BaseFragment<FragmentSearchBinding>() {

    private val viewModel: SearchViewModel by viewModels()

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSearchBinding {
        return FragmentSearchBinding.inflate(inflater, container, false)
    }

    private fun setKeywordEnter(){
        binding.etSearchKeyword.setOnKeyListener{v, keyCode, event ->
            if(event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER){
                val keyword: String by lazy{
                    if((binding.etSearchKeyword.text.toString().isNullOrBlank())){
                        return@lazy ""
                    }else{
                        return@lazy ""
                    }
                }

                binding.etSearchKeyword.clearFocus()
                binding.etSearchKeyword.requestFocus()
//                binding.etSearchKeyword.text = keyword
            }
            return@setOnKeyListener false
        }
    }

    private fun setRecyclerView(){

    }

}