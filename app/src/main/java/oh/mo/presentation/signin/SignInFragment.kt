package oh.mo.presentation.signin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import oh.mo.R
import oh.mo.databinding.FragmentSignInBinding
import oh.mo.presentation.base.BaseFragment

class SignInFragment : BaseFragment<FragmentSignInBinding, SignInViewModel>() {

    override val viewModel: SignInViewModel by viewModels()
    override val layoutResourceId: Int
        get() = R.layout.fragment_sign_in

    override fun initStartView() {}
    override fun initBinding() {}
    override fun initAfterBinding() {}

}