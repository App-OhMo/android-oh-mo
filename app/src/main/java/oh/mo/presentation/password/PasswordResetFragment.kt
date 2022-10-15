package oh.mo.presentation.password

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.launch
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import oh.mo.R
import oh.mo.databinding.FragmentPasswordResetBinding
import oh.mo.presentation.base.BaseFragment
import oh.mo.utils.textChangesToFlow
import kotlin.coroutines.CoroutineContext

class PasswordResetFragment : BaseFragment<FragmentPasswordResetBinding>() {
    private val viewModel: PasswordResetViewModel by viewModels()

    private var myCoroutineJob: Job = Job()
    private val myCoroutineContext: CoroutineContext
        get() = Dispatchers.IO + myCoroutineJob

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentPasswordResetBinding {
        return FragmentPasswordResetBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFunctions()
    }

    @OptIn(FlowPreview::class)
    private fun initFunctions() {
        binding.apply {
            tvPasswordReset.isEnabled = false

            tvPasswordReset.setOnClickListener {
                it.findNavController().popBackStack(R.id.fragment_start, false)
            }

            CoroutineScope(Dispatchers.IO).launch(myCoroutineContext) {
                etPasswordResetNew.textChangesToFlow()
                    .debounce(1500)
                    .filter {
                        it?.length!! > 0
                    }
                    .onEach {
                        if (it?.length!! >= 8) {
                            root.post {
                                tvPasswordResetWrongPasswordFormat.visibility = View.GONE
                            }
                        } else {
                            root.post {
                                tvPasswordResetWrongPasswordFormat.visibility = View.VISIBLE
                            }
                        }
                    }
                    .launchIn(this)

                etPasswordResetConfirm.textChangesToFlow()
                    .debounce(1000)
                    .filter {
                        it?.length!! > 0
                    }
                    .onEach {
                        if (it.toString() == etPasswordResetNew.text.toString()) {
                            root.post {
                                tvPasswordResetPasswordNotMatching.visibility = View.GONE
                                tvPasswordReset.isEnabled = true
                            }
                        } else {
                            root.post {
                                tvPasswordResetPasswordNotMatching.visibility = View.VISIBLE
                                tvPasswordReset.isEnabled = false
                            }
                        }
                    }
                    .launchIn(this)
            }
        }
    }
}