package oh.mo.presentation.signup

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import oh.mo.databinding.FragmentSignUpBinding
import oh.mo.presentation.base.BaseFragment

class SignUpFragment : BaseFragment<FragmentSignUpBinding>() {

    private val viewModel: SignUpViewModel by viewModels()
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentSignUpBinding {
        return FragmentSignUpBinding.inflate(inflater, container, false)
    }
}