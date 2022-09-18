package oh.mo.presentation.home

import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.AbsoluteSizeSpan
import android.util.Log
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import oh.mo.R
import oh.mo.databinding.FragmentHomeBinding
import oh.mo.presentation.base.BaseFragment
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    private val currentTime = LocalDateTime.now()
    private val currentHour = currentTime.format(DateTimeFormatter.ofPattern("HH")).toInt()

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
        binding.tvHomeDate.text = currentTime.format(
            DateTimeFormatter
                .ofPattern("yyyy-MM-dd\nE요일")
                .withLocale(Locale.KOREAN)
        )
    }

    private fun getApiResponse() {
        viewModel.getApi(
            currentTime.minusDays(1).format(
                DateTimeFormatter
                    .ofPattern("yyyyMMdd")
                    .withLocale(Locale.KOREAN)
            )
        )
        
        lifecycleScope.launchWhenStarted {
            viewModel.list.collect { list ->
                setTemperature(
                    textView = binding.tvHomeCurrentTemperature,
                    text = list?.filter {
                        it?.category == "TMP"
                    }
                        ?.get(currentHour)
                        ?.fcstValue
                        ?: "--",
                    celsiusSize = 20
                )

                setTemperature(
                    textView = binding.tvHomeHighestTemperature,
                    text = list?.filter {
                        it?.category == "TMX"
                    }
                        ?.get(0)
                        ?.fcstValue
                        ?: "--",
                    celsiusSize = 10
                )

                setTemperature(
                    textView = binding.tvHomeMinimumTemperature,
                    text = list?.filter {
                        it?.category == "TMN"
                    }
                        ?.get(0)
                        ?.fcstValue
                        ?: "--",
                    celsiusSize = 10
                )

                when (list?.filter { it?.category == "PTY" }?.get(currentHour)?.fcstValue) {
                    "0" -> {
                        when (list.filter { it?.category == "SKY" }[currentHour]?.fcstValue) {
                            "1", "3" -> {
                                setCurrentWeather("sunny")
                            }
                            "4" -> {
                                setCurrentWeather("suncloud")
                            }
                        }
                    }

                    "1", "4" -> {
                        setCurrentWeather("rainy")
                    }
                    "2", "3" -> {
                        setCurrentWeather("snowy")
                    }
                }

                list?.forEach { it ->
                    Log.d("Whole List", it.toString())
                }
            }
        }
    }

    private fun setTemperature(textView: TextView, text: String, celsiusSize: Int) {
        textView.text =
            SpannableStringBuilder(resources.getString(R.string.home_current_temperature, text))
                .also {
                    it.setSpan(AbsoluteSizeSpan(celsiusSize, true),
                        it.split("℃")[0].length,
                        it.split("℃")[0].length + 1,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                }
    }

    private fun setCurrentWeather(sky: String) {
        binding.apply {
            when (sky) {
                "sunny" -> {
                    ivHomeCurrentWeather.setImageResource(
                        R.drawable.home_present_weather_graphic_sunny
                    )
                    tlHomeFilterTab.getTabAt(0)?.select()
                }

                "suncloud" -> {
                    ivHomeCurrentWeather.setImageResource(
                        R.drawable.home_present_weather_graphic_suncloud
                    )
                    tlHomeFilterTab.getTabAt(0)?.select()
                }

                "cloudy" -> {
                    ivHomeCurrentWeather.setImageResource(
                        R.drawable.home_present_weather_graphic_cloudy
                    )
                    tlHomeFilterTab.getTabAt(1)?.select()
                }

                "rainy" -> {
                    ivHomeCurrentWeather.setImageResource(
                        R.drawable.home_present_weather_graphic_rainy
                    )
                    tlHomeFilterTab.getTabAt(2)?.select()
                }

                "snowy" -> {
                    ivHomeCurrentWeather.setImageResource(
                        R.drawable.home_present_weather_graphic_snowy
                    )
                    tlHomeFilterTab.getTabAt(3)?.select()
                }
            }
        }
    }
}