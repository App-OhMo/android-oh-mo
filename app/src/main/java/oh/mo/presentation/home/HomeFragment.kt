package oh.mo.presentation.home

import androidx.recyclerview.widget.GridLayoutManager
import oh.mo.R
import oh.mo.databinding.FragmentHomeBinding
import oh.mo.presentation.base.BaseFragment

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
}