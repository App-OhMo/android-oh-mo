package oh.mo.presentation.signup

import androidx.fragment.app.viewModels
import oh.mo.R
import oh.mo.databinding.FragmentSignUpBinding
import oh.mo.presentation.base.BaseFragment

class SignUpFragment : BaseFragment<FragmentSignUpBinding, SignUpViewModel>() {

    override val viewModel: SignUpViewModel by viewModels()
    override val layoutResourceId: Int
        get() = R.layout.fragment_sign_up

    override fun initStartView() {}
    override fun initBinding() {}
    override fun initAfterBinding() {}

}