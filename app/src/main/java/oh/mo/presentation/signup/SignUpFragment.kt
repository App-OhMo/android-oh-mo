package oh.mo.presentation.signup

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
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import oh.mo.R
import oh.mo.databinding.FragmentSignUpBinding
import oh.mo.presentation.base.BaseFragment
import oh.mo.utils.setTextViewEnabled
import oh.mo.utils.textChangesToFlow
import kotlin.coroutines.CoroutineContext

@AndroidEntryPoint
class SignUpFragment : BaseFragment<FragmentSignUpBinding>() {

    private val viewModel: SignUpViewModel by viewModels()

    private var isNicknameValid = false
    private var isPasswordValid = false
    private var isEmailValid = false
    private var nickname = ""
    private var email = ""

    private val myCoroutineJob: Job = Job()
    private val myCoroutineContext: CoroutineContext
        get() = IO + myCoroutineJob

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentSignUpBinding {
        return FragmentSignUpBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFunctions()
        initFlowFunctions()
        initCoroutineScope()
    }

    private fun initFunctions() {
        binding.apply {
            tvSignup.isEnabled = false

            tvSignupNicknameDuplicateCheck.setOnClickListener {
                if (etSignupNickname.text.length > 8) {
                    setVisibilityByValidation("nicknameLength")
                } else {
                    viewModel.postIsNickNameDuplicated(etSignupNickname.text.toString())
                }
            }

            tvSignupEmailDuplicateCheck.setOnClickListener {
                viewModel.postIsEmailDuplicated(etSignupEmail.text.toString())
            }

            tvSignup.setOnClickListener {
                viewModel.postUserSignUp(
                    nickName = etSignupNickname.text.toString(),
                    email = etSignupEmail.text.toString(),
                    password = etSignupPassword.text.toString(),
                    toastMsg = { string ->
                        Toast.makeText(requireContext(), string, Toast.LENGTH_SHORT).show()
                    },
                    exit = { it.findNavController().popBackStack() }
                )
            }
        }
    }

    private fun initFlowFunctions() {
        binding.apply {
            lifecycleScope.launch {
                viewModel.isNicknameValid.collect {
                    if (!it) {
                        setVisibilityByValidation("availableNickname")
                        isNicknameValid = true
                        nickname = etSignupNickname.text.toString()
                        setBtnEnabled()
                    } else {
                        setVisibilityByValidation("duplicatedNickname")
                        isNicknameValid = false
                        nickname = etSignupNickname.text.toString()
                        setBtnEnabled()
                    }
                }
            }

            lifecycleScope.launch {
                viewModel.isEmailValid.collect {
                    if (!it) {
                        setVisibilityByValidation("availableEmail")
                        isEmailValid = true
                        email = etSignupEmail.text.toString()
                        setBtnEnabled()
                    } else {
                        setVisibilityByValidation("duplicatedEmail")
                        isEmailValid = false
                        email = etSignupEmail.text.toString()
                        setBtnEnabled()
                    }
                }
            }

            lifecycleScope.launch {
                viewModel.isBtnEnabled.collect {
                    tvSignup.isEnabled = it[0] && it[1] && it[2]
                }
            }
        }
    }

    private fun initCoroutineScope() {
        binding.apply {
            CoroutineScope(IO).launch(myCoroutineContext) {
                etSignupNickname.textChangesToFlow()
                    .onEach {
                        if ((it ?: "").isNotEmpty()) {
                            root.post {
                                tvSignupNicknameDuplicateCheck.isEnabled = true
                                tvSignupNicknameDuplicateCheck.setTextViewEnabled(true)
                            }
                        } else {
                            root.post {
                                tvSignupNicknameDuplicateCheck.isEnabled = false
                                tvSignupNicknameDuplicateCheck.setTextViewEnabled(false)
                            }
                        }

                        if (it.toString() != nickname) {
                            isNicknameValid = false
                            root.post { tvSignupNicknameValidation.text = "" }
                            setBtnEnabled()
                        } else if (it.toString() == nickname && (it ?: "").isNotEmpty()) {
                            isNicknameValid = true
                            setBtnEnabled()
                        }
                    }
                    .launchIn(this)

                etSignupPassword.textChangesToFlow()
                    .onEach {
                        if ((it ?: "").isNotEmpty()) {
                            root.post {
                                isPasswordValid =
                                    it?.matches("^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[\$@!%*#?&]).{8,15}.\$".toRegex()) == true
                                if (isPasswordValid) {
                                    setVisibilityByValidation("availablePassword")
                                    setBtnEnabled()
                                } else {
                                    setVisibilityByValidation("wrongPasswordFormat")
                                    setBtnEnabled()
                                }
                            }
                        }
                    }
                    .launchIn(this)

                etSignupEmail.textChangesToFlow()
                    .onEach {
                        if ((it ?: "").isNotEmpty()) {
                            if (android.util.Patterns.EMAIL_ADDRESS.matcher(it.toString())
                                    .matches() && it?.length!! > 0
                            ) {
                                root.post {
                                    tvSignupEmailDuplicateCheck.isEnabled = true
                                    tvSignupEmailDuplicateCheck.setTextViewEnabled(true)

                                    tvSignupEmailValidation.text = ""
                                }
                            } else {
                                root.post {
                                    tvSignupEmailDuplicateCheck.isEnabled = false
                                    tvSignupEmailDuplicateCheck.setTextViewEnabled(false)

                                    tvSignupEmailValidation.text =
                                        getString(R.string.wrong_email_format)
                                    tvSignupEmailValidation.setTextColor(resources.getColor(R.color.red_f5,
                                        null))
                                }
                            }
                        }

                        if (it.toString() != email) {
                            isEmailValid = false
                            setBtnEnabled()
                        } else if (it.toString() == email && (it ?: "").isNotEmpty()) {
                            isEmailValid = true
                            setBtnEnabled()
                        }
                    }
                    .launchIn(this)
            }
        }
    }

    private fun setBtnEnabled() {
        viewModel.setBtnEnabled(isNicknameValid, isPasswordValid, isEmailValid)
    }

    private fun setVisibilityByValidation(view: String) {
        binding.apply {
            when (view) {
                "nicknameLength" -> {
                    tvSignupNicknameValidation.text = getString(R.string.nickname_length_error)
                    tvSignupNicknameValidation.setTextColor(resources.getColor(R.color.red_f5,
                        null))
                }

                "duplicatedNickname" -> {
                    tvSignupNicknameValidation.text = getString(R.string.nickname_duplicated)
                    tvSignupNicknameValidation.setTextColor(resources.getColor(R.color.red_f5,
                        null))
                }

                "availableNickname" -> {
                    tvSignupNicknameValidation.text = getString(R.string.nickname_available)
                    tvSignupNicknameValidation.setTextColor(resources.getColor(R.color.blue_4e,
                        null))
                }

                "availablePassword" -> {
                    tvSignupPasswordValidation.visibility = View.GONE
                }

                "wrongPasswordFormat" -> {
                    tvSignupPasswordValidation.visibility = View.VISIBLE
                }

                "duplicatedEmail" -> {
                    tvSignupEmailValidation.text = getString(R.string.email_duplicated)
                    tvSignupEmailValidation.setTextColor(resources.getColor(R.color.red_f5,
                        null))
                }

                "availableEmail" -> {
                    tvSignupEmailValidation.text = getString(R.string.email_available)
                    tvSignupEmailValidation.setTextColor(resources.getColor(R.color.blue_4e,
                        null))
                }
            }
        }
    }
}