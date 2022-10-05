package oh.mo.presentation.start

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import oh.mo.R
import oh.mo.databinding.FragmentStartBinding
import oh.mo.presentation.base.BaseFragment

class StartFragment : BaseFragment<FragmentStartBinding>() {

    private val viewModel: StartViewModel by viewModels()
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentStartBinding {
        return FragmentStartBinding.inflate(inflater, container, false)
    }
}