package oh.mo.presentation.posting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.chip.Chip
import oh.mo.databinding.FragmentCoordiChipBinding
import oh.mo.presentation.base.BaseFragment

class CoordiChipFragment : BaseFragment<FragmentCoordiChipBinding>() {
    private val viewModel: PostingViewModel by viewModels()

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCoordiChipBinding {
        return FragmentCoordiChipBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showChip()
    }

    private fun showChip() {
        binding.tvPostingTop.setOnClickListener { setChip(viewModel.chipOfTop) }
        binding.tvPostingBottom.setOnClickListener { setChip(viewModel.chipOfBottom) }
        binding.tvPostingOuter.setOnClickListener { setChip(viewModel.chipOfOuter) }
        binding.tvPostingShoe.setOnClickListener { setChip(viewModel.chipOfShoe) }
        binding.tvPostingEtc.setOnClickListener { setChip(viewModel.chipOfEtc) }

    }

    private fun setChip(data: List<String>) {
        binding.chipPosting.removeAllViews()

        data.forEach {
            var chip = Chip(requireContext())
            chip.text = it
            binding.chipPosting.addView(chip)
        }
    }
}