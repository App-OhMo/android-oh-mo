package oh.mo.presentation.signin

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import oh.mo.R
import oh.mo.databinding.FragmentSignInBinding
import oh.mo.presentation.base.BaseFragment

class SignInFragment : BaseFragment<FragmentSignInBinding>() {

    private val viewModel: SignInViewModel by viewModels()

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentSignInBinding {
        return FragmentSignInBinding.inflate(inflater, container, false)
    }
}