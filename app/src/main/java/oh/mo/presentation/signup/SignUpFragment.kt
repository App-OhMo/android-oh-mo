package oh.mo.presentation.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import oh.mo.databinding.FragmentSignUpBinding
import oh.mo.presentation.base.BaseFragment
import oh.mo.utils.setTextViewEnabled
import oh.mo.utils.textChangesToFlow
import kotlin.coroutines.CoroutineContext

class SignUpFragment : BaseFragment<FragmentSignUpBinding>() {

    private val viewModel: SignUpViewModel by viewModels()

    private var myCoroutineJob: Job = Job()
    private val myCoroutineContext: CoroutineContext
        get() = Dispatchers.IO + myCoroutineJob

    private var isNicknameValid = false
    private var isPasswordValid = false
    private var isEmailValid = false

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentSignUpBinding {
        return FragmentSignUpBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFunctions()
    }

    private fun initFunctions() {
        binding.apply {
            tvSignupNicknameDuplicateCheck.setOnClickListener {
                // 닉네임 중복체크 (임시)
                isNicknameValid = true
                Toast.makeText(requireContext(), "닉네임 중복체크!", Toast.LENGTH_SHORT).show()
                viewModel.setBtnEnabled(
                    isNicknameValid,
                    isPasswordValid,
                    isEmailValid
                )
            }

            tvSignupEmailDuplicateCheck.setOnClickListener {
                // 이메일 중복체크 (임시)
                isEmailValid = true
                Toast.makeText(requireContext(), "이메일 중복체크!", Toast.LENGTH_SHORT).show()
                viewModel.setBtnEnabled(
                    isNicknameValid,
                    isPasswordValid,
                    isEmailValid
                )
            }

            tvSignup.setOnClickListener {
                // 새로운 유저정보 서버에 추가 (임시)
                Toast.makeText(requireContext(), "회원가입 완료!", Toast.LENGTH_SHORT).show()
                it.findNavController().popBackStack()
            }

            tvSignup.isEnabled = false

            lifecycleScope.launchWhenStarted {
                viewModel.isBtnEnabled.collect {
                    tvSignup.isEnabled = it[0] && it[1] && it[2]
                }
            }

            CoroutineScope(Dispatchers.IO).launch(myCoroutineContext) {
                etSignupNickname.textChangesToFlow()
                    .onEach {
                        if (it?.length!! == 0) {
                            root.post {
                                tvSignupNicknameDuplicateCheck.isEnabled = false
                                tvSignupNicknameDuplicateCheck.setTextViewEnabled(false)
                            }
                        } else {
                            root.post {
                                tvSignupNicknameDuplicateCheck.isEnabled = true
                                tvSignupNicknameDuplicateCheck.setTextViewEnabled(true)
                            }
                        }
                    }
                    .launchIn(this)

                etSignupPassword.textChangesToFlow()
                    .onEach {
                        if (it?.length!! >= 8) {
                            isPasswordValid = true
                            viewModel.setBtnEnabled(
                                isNicknameValid,
                                isPasswordValid,
                                isEmailValid
                            )
                        } else {
                            isPasswordValid = false
                            viewModel.setBtnEnabled(
                                isNicknameValid,
                                isPasswordValid,
                                isEmailValid
                            )
                        }

                    }
                    .launchIn(this)

                etSignupEmail.textChangesToFlow()
                    .onEach {
                        if (it?.length!! == 0) {
                            root.post {
                                tvSignupEmailDuplicateCheck.isEnabled = false
                                tvSignupEmailDuplicateCheck.setTextViewEnabled(false)
                            }
                        } else {
                            root.post {
                                tvSignupEmailDuplicateCheck.isEnabled = true
                                tvSignupEmailDuplicateCheck.setTextViewEnabled(true)
                            }
                        }
                    }
                    .launchIn(this)
            }
        }
    }
}