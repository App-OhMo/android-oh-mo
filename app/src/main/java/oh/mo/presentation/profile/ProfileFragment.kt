package oh.mo.presentation.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import oh.mo.R
import oh.mo.databinding.FragmentProfileBinding
import oh.mo.presentation.adapter.PhotoRcvAdapter
import oh.mo.presentation.base.BaseFragment
import oh.mo.utils.VHBindingByType


class ProfileFragment : BaseFragment<FragmentProfileBinding>() {
    private val viewModel: ProfileViewModel by viewModels()

    private val todayPhotoAdapter by lazy { PhotoRcvAdapter(VHBindingByType.TODAY_WEATHER) }
    private val postPhotoAdapter by lazy { PhotoRcvAdapter(VHBindingByType.POST) }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentProfileBinding {
        return FragmentProfileBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRcv()
        setRcvData()
    }

    private fun initRcv() {
        binding.rcvProfileTodayWeather.adapter = todayPhotoAdapter
        binding.rcvProfilePost.adapter = postPhotoAdapter
    }

    private fun setRcvData() {
        val list = listOf(
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background
        )

        lifecycleScope.launchWhenStarted {
            todayPhotoAdapter.submitList(list)
            postPhotoAdapter.submitList(list)
        }
    }
}