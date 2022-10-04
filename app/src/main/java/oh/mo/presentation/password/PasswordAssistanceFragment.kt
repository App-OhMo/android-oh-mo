package oh.mo.presentation.password

import androidx.fragment.app.viewModels
import oh.mo.R
import oh.mo.databinding.FragmentPasswordAssistanceBinding
import oh.mo.presentation.base.BaseFragment

class PasswordAssistanceFragment : BaseFragment<FragmentPasswordAssistanceBinding, PasswordAssistanceViewModel>() {

    override val viewModel: PasswordAssistanceViewModel by viewModels()
    override val layoutResourceId: Int
        get() = R.layout.fragment_password_assistance

    override fun initStartView() {}
    override fun initBinding() {}
    override fun initAfterBinding() {}
}