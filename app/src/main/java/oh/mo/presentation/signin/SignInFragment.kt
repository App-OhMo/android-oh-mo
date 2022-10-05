package oh.mo.presentation.signin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFunctions()
    }

    private fun initFunctions() {
        binding.apply {
            tvSignin.setOnClickListener {
                it.findNavController().navigate(R.id.action_signin_to_main)
            }

            tvSigninPasswordSearch.setOnClickListener {
                it.findNavController().navigate(R.id.action_signin_to_password_assistance)
            }
        }
    }
}