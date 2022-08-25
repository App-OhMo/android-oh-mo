package oh.mo.presentation.home

import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.AbsoluteSizeSpan
import androidx.recyclerview.widget.GridLayoutManager
import oh.mo.R
import oh.mo.databinding.FragmentHomeBinding
import oh.mo.presentation.base.BaseFragment
import java.time.LocalDate


class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    override val viewModel: HomeViewModel
        get() = HomeViewModel()
    override val layoutResourceId: Int
        get() = R.layout.fragment_home

    override fun initStartView() {

    }

    override fun initBinding() {

    }

    override fun initAfterBinding() {
        setRecyclerView()
        setCurrentDate()
        setCurrentTemperature()
    }

    private fun setRecyclerView() {
        binding.rvHomeRecommendationsFeed.apply {
            adapter = RecommendationsFeedAdapter()
            layoutManager = GridLayoutManager(requireContext(), 2)
            addItemDecoration(RecommendationsFeedDecoration(requireContext()))
        }

        //dummy data
        val list = mutableListOf<String>()
            .also {
                for (i in 1..7) {
                    it.add("Recommendation $i")
                }
            }

        (binding.rvHomeRecommendationsFeed.adapter as RecommendationsFeedAdapter)
            .differ
            .submitList(list)
    }

    private fun setCurrentDate() {
        val currentTime: LocalDate = LocalDate.now()

        binding.tvHomeDate.text =
            resources
                .getString(
                    R.string.home_current_date,
                    currentTime.year.toString(),
                    currentTime.monthValue.toString().padStart(2, '0'),
                    currentTime.dayOfMonth.toString().padStart(2, '0'),
                    currentTime.dayOfWeek.toString()
                )
    }

    private fun setCurrentTemperature() {
        binding.tvHomeCurrentTemperature.text =
            SpannableStringBuilder(resources.getString(R.string.home_current_temperature, 23))
                .also {
                    it.setSpan(AbsoluteSizeSpan(20, true),
                        it.split("℃")[0].length,
                        it.split("℃")[0].length + 1,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                }
    }
}