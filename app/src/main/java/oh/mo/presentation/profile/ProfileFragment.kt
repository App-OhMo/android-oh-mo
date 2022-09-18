package oh.mo.presentation.profile

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import oh.mo.R
import oh.mo.databinding.FragmentProfileBinding
import oh.mo.presentation.adapter.PhotoRcvAdapter
import oh.mo.presentation.base.BaseFragment
import oh.mo.utils.VHBindingByType


class ProfileFragment : BaseFragment<FragmentProfileBinding, ProfileViewModel>() {

    override val viewModel: ProfileViewModel by viewModels()
    override val layoutResourceId: Int = R.layout.fragment_profile
    private val todayPhotoAdapter by lazy { PhotoRcvAdapter(VHBindingByType.TODAY_WEATHER) }
    private val postPhotoAdapter by lazy { PhotoRcvAdapter(VHBindingByType.POST) }

    override fun initStartView() {
        initRcv()
        setRcvData()
    }

    override fun initBinding() {

    }

    override fun initAfterBinding() {

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
            R.drawable.ic_launcher_background
        )

        lifecycleScope.launchWhenStarted {
            todayPhotoAdapter.submitList(list)
            postPhotoAdapter.submitList(list)
        }
    }
}