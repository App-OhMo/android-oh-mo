package oh.mo.presentation.posting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import oh.mo.databinding.FragmentPostingBinding
import oh.mo.presentation.base.BaseFragment

class PostingFragment : BaseFragment<FragmentPostingBinding>() {
    private val viewModel: PostingViewModel by viewModels()

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentPostingBinding {
        return FragmentPostingBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initVp()
    }

    private fun initRcv() {

    }

    private fun initVp() {
        val fmAdapter = PostingFragmentStateAdapter(requireActivity())
        binding.vpPostingContent.adapter = fmAdapter
    }

}