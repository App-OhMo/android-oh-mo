package oh.mo.presentation.password

import androidx.fragment.app.viewModels
import oh.mo.R
import oh.mo.databinding.FragmentPasswordResetBinding
import oh.mo.presentation.base.BaseFragment

class PasswordResetFragment : BaseFragment<FragmentPasswordResetBinding,PasswordResetViewModel>() {

    override val viewModel: PasswordResetViewModel by viewModels()
    override val layoutResourceId: Int
        get() = R.layout.fragment_password_reset

    override fun initStartView() {}
    override fun initBinding() {}
    override fun initAfterBinding() {}

}