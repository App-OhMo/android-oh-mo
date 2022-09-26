package oh.mo.presentation.start

import androidx.fragment.app.viewModels
import oh.mo.R
import oh.mo.databinding.FragmentStartBinding
import oh.mo.presentation.base.BaseFragment

class StartFragment : BaseFragment<FragmentStartBinding, StartViewModel>() {

    override val viewModel: StartViewModel by viewModels()
    override val layoutResourceId: Int
        get() = R.layout.fragment_start

    override fun initStartView() {

    }

    override fun initBinding() {

    }

    override fun initAfterBinding() {

    }
}