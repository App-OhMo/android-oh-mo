package oh.mo.presentation.password

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import oh.mo.R
import oh.mo.databinding.FragmentPasswordAssistanceBinding
import oh.mo.presentation.base.BaseFragment
import oh.mo.utils.textChangesToFlow
import kotlin.coroutines.CoroutineContext

class PasswordAssistanceFragment : BaseFragment<FragmentPasswordAssistanceBinding>() {

    private val viewModel: PasswordAssistanceViewModel by viewModels()

    private var myCoroutineJob: Job = Job()
    private val myCoroutineContext: CoroutineContext
        get() = Dispatchers.IO + myCoroutineJob

    private var isNicknameValid = false
    private var isEmailValid = false

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentPasswordAssistanceBinding {
        return FragmentPasswordAssistanceBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFunctions()
    }

    private fun initFunctions() {
        binding.apply {
            tvPasswordAssistanceSendVerificationCode.isEnabled = false
            tvPasswordAssistanceNext.isEnabled = false

            tvPasswordAssistanceNext.setOnClickListener {
                it.findNavController().navigate(R.id.action_password_assistance_to_password_reset)
            }

            tvPasswordAssistanceSendVerificationCode.setOnClickListener {
                tvPasswordAssistanceVerificationCode.visibility = View.VISIBLE
                etPasswordAssistanceVerificationCode.visibility = View.VISIBLE
                tvPasswordAssistanceNext.visibility = View.VISIBLE
            }

            lifecycleScope.launchWhenStarted {
                viewModel.isBtnEnabled.collect {
                    tvPasswordAssistanceSendVerificationCode.isEnabled = it[0] && it[1]
                }
            }

            CoroutineScope(Dispatchers.IO).launch(myCoroutineContext) {
                etPasswordAssistanceNickname.textChangesToFlow()
                    .onEach {
                        if (it?.length!! == 0) {
                            isNicknameValid = false
                            viewModel.setBtnEnabled(isNicknameValid, isEmailValid)
                        } else {
                            isNicknameValid = true
                            viewModel.setBtnEnabled(isNicknameValid, isEmailValid)
                        }
                    }
                    .launchIn(this)

                etPasswordAssistanceEmail.textChangesToFlow()
                    .onEach {
                        if (android.util.Patterns.EMAIL_ADDRESS.matcher(it.toString())
                                .matches() && it?.length!! > 0
                        ) {
                            isEmailValid = true
                            viewModel.setBtnEnabled(isNicknameValid, isEmailValid)
                        } else {
                            isEmailValid = false
                            viewModel.setBtnEnabled(isNicknameValid, isEmailValid)
                        }
                    }
                    .launchIn(this)

                etPasswordAssistanceVerificationCode.textChangesToFlow()
                    .onEach {
                        root.post {
                            // 인증코드 불러오기
                            tvPasswordAssistanceNext.isEnabled = it.toString() == "000000"
                        }
                    }
                    .launchIn(this)
            }
        }
    }
}