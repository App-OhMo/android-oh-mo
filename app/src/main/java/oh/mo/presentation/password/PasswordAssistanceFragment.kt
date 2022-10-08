package oh.mo.presentation.password

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import oh.mo.databinding.FragmentPasswordAssistanceBinding
import oh.mo.presentation.base.BaseFragment

class PasswordAssistanceFragment : BaseFragment<FragmentPasswordAssistanceBinding>() {

    private val viewModel: PasswordAssistanceViewModel by viewModels()

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentPasswordAssistanceBinding {
        return FragmentPasswordAssistanceBinding.inflate(inflater, container, false)
    }
}