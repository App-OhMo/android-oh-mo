package oh.mo.presentation.login

import androidx.activity.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import oh.mo.R
import oh.mo.databinding.ActivityLoginBinding
import oh.mo.presentation.base.BaseActivity

class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>() {
    override val viewModel: LoginViewModel by viewModels()
    override val layoutResourceId: Int
        get() = R.layout.activity_login

    override fun initStartView() {
        setNavigation()
    }

    override fun initBinding() {

    }

    override fun initAfterBinding() {

    }

    private fun setNavigation() {

    }
}