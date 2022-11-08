package oh.mo.presentation.signin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import oh.mo.R
import oh.mo.databinding.FragmentSignInBinding
import oh.mo.presentation.base.BaseFragment
import oh.mo.utils.textChangesToFlow
import kotlin.coroutines.CoroutineContext

@AndroidEntryPoint
class SignInFragment : BaseFragment<FragmentSignInBinding>() {

    private val viewModel: SignInViewModel by viewModels()

    private var myCoroutineJob: Job = Job()
    private val myCoroutineContext: CoroutineContext
        get() = IO + myCoroutineJob

    private var isEmailValid = false
    private var isPasswordValid = false

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentSignInBinding {
        return FragmentSignInBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFunctions()
        initFlowFunctions()
        initCoroutineScope()
    }

    private fun initFunctions() {
        binding.apply {
            tvSignin.isEnabled = false

            tvSignin.setOnClickListener {
                viewModel.postUserSignIn(
                    email = etSigninEmail.text.toString(),
                    password = etSigninPassword.text.toString(),
                    toastMsg = { string ->
                        Toast.makeText(requireContext(), string, Toast.LENGTH_SHORT).show()
                    },
                    exit = {
                        it.findNavController().navigate(R.id.action_signin_to_main)
                        requireActivity().finish()
                    }
                )
            }

            tvSigninPasswordSearch.setOnClickListener {
                it.findNavController().navigate(R.id.action_signin_to_password_assistance)
            }
        }
    }

    private fun initFlowFunctions() {
        binding.apply {
            lifecycleScope.launch {
                viewModel.isBtnEnabled.collect {
                    tvSignin.isEnabled = it[0] && it[1]
                }
            }
        }
    }

    private fun initCoroutineScope() {
        binding.apply {
            CoroutineScope(IO).launch(myCoroutineContext) {
                etSigninEmail.textChangesToFlow()
                    .filter {
                        it?.length!! > 0
                    }
                    .onEach {
                        root.post {
                            if (android.util.Patterns.EMAIL_ADDRESS.matcher(it.toString())
                                    .matches() && it?.length!! > 0
                            ) {
                                tvSigninWrongEmailFormat.visibility = View.GONE
                                isEmailValid = true
                            } else {
                                tvSigninWrongEmailFormat.visibility = View.VISIBLE
                                isEmailValid = false
                            }

                            setBtnEnabled()
                        }
                    }
                    .launchIn(this)

                etSigninPassword.textChangesToFlow()
                    .onEach {
                        isPasswordValid = it?.length!! != 0
                        setBtnEnabled()
                    }
                    .launchIn(this)
            }
        }
    }

    private fun setBtnEnabled() {
        viewModel.setBtnEnabled(isEmailValid, isPasswordValid)
    }

    override fun onDestroy() {
        super.onDestroy()
        myCoroutineContext.cancel()
    }
}



