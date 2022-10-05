package oh.mo.presentation.password

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import oh.mo.databinding.FragmentPasswordResetBinding
import oh.mo.presentation.base.BaseFragment

class PasswordResetFragment : BaseFragment<FragmentPasswordResetBinding>() {
    private val viewModel: PasswordResetViewModel by viewModels()

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentPasswordResetBinding {
        return FragmentPasswordResetBinding.inflate(inflater, container, false)
    }
}