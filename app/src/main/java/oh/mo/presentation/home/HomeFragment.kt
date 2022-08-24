package oh.mo.presentation.home

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

    }
}