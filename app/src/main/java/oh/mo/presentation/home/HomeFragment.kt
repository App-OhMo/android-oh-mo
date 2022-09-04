package oh.mo.presentation.home

import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.AbsoluteSizeSpan
import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import oh.mo.R
import oh.mo.databinding.FragmentHomeBinding
import oh.mo.presentation.base.BaseFragment
import java.time.LocalDate

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    override val viewModel: HomeViewModel by viewModels()
    override val layoutResourceId: Int
        get() = R.layout.fragment_home

    override fun initStartView() {

    }

    override fun initBinding() {

    }

    override fun initAfterBinding() {
        setRecyclerView()
        setCurrentDate()
        getApiResponse()
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
        val currentTime = LocalDate.now()

        binding.tvHomeDate.text = resources.getString(
            R.string.home_current_date,
            currentTime.year.toString(),
            currentTime.monthValue.toString().padStart(2, '0'),
            currentTime.dayOfMonth.toString().padStart(2, '0'),
            currentTime.dayOfWeek.toString()
        )
    }

    private fun setCurrentTemperature(text: String) {
        binding.tvHomeCurrentTemperature.text =
            SpannableStringBuilder(resources.getString(R.string.home_current_temperature, text))
                .also {
                    it.setSpan(AbsoluteSizeSpan(20, true),
                        it.split("℃")[0].length,
                        it.split("℃")[0].length + 1,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                }
    }

    private fun getApiResponse() {
        viewModel.getApi()

        lifecycleScope.launchWhenStarted {
            viewModel.list.collect { list ->
                setCurrentTemperature(
                    list?.filter {
                        it?.category == "TMP"
                    }?.get(0)?.fcstValue ?: ""
                )

                list?.forEach { it ->
                    Log.d("Whole List", it.toString())
                }
            }
        }
    }
}