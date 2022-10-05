package oh.mo.presentation.start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import oh.mo.R
import oh.mo.databinding.FragmentStartBinding
import oh.mo.presentation.base.BaseFragment

class StartFragment : BaseFragment<FragmentStartBinding>() {

    private val viewModel: StartViewModel by viewModels()

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentStartBinding {
        return FragmentStartBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFunctions()
    }

    private fun initFunctions() {
        binding.apply {
            tvLoginSignin.setOnClickListener {
                it.findNavController().navigate(R.id.action_login_to_signin)
            }

            tvLoginSignup.setOnClickListener {
                it.findNavController().navigate(R.id.action_login_to_signup)
            }
        }
    }
}