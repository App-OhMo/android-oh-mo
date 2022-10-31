package oh.mo.presentation.signup

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import oh.mo.data.api.ServerApi
import oh.mo.data.model.remote.request.EmailDuplicateCheckRequest
import oh.mo.data.model.remote.request.NicknameDuplicateCheckRequest
import oh.mo.data.model.remote.request.SignUpRequest
import oh.mo.di.NetworkModule
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    @NetworkModule.ServerRetrofit private val serverApi: ServerApi,
) : ViewModel() {

    private val _isNicknameValid = MutableSharedFlow<Boolean>()
    val isNicknameValid = _isNicknameValid.asSharedFlow()

    private val _isEmailValid = MutableSharedFlow<Boolean>()
    val isEmailValid = _isEmailValid.asSharedFlow()

    private val _isBtnEnabled = MutableSharedFlow<List<Boolean>>()
    val isBtnEnabled = _isBtnEnabled.asSharedFlow()

    fun setBtnEnabled(
        isNicknameValid: Boolean,
        isPasswordValid: Boolean,
        isEmailValid: Boolean,
    ) {
        viewModelScope.launch {
            _isBtnEnabled.emit(listOf(isNicknameValid, isPasswordValid, isEmailValid))
        }
    }

    fun postUserSignUp(
        nickName: String,
        email: String,
        password: String,
        toastMsg: (String) -> Unit,
        exit: () -> Unit,
    ) {
        viewModelScope.launch {
            runCatching {
                serverApi.postUserSignUp(
                    SignUpRequest(
                        nickName,
                        email,
                        password
                    )
                )
            }.onSuccess {
                Log.d("test", it.toString())
                toastMsg(it.responseMessage)
                exit()
            }.onFailure {
                toastMsg("회원가입 실패")
                Log.d("test", it.toString())
            }
        }
    }

    fun postIsNickNameDuplicated(n: String) {
        viewModelScope.launch {
            runCatching {
                serverApi.postIsNicknameDuplicated(NicknameDuplicateCheckRequest(n))
            }.onSuccess {
                _isNicknameValid.emit(it)
            }.onFailure {
                Log.d("test", it.toString())
            }
        }
    }

    fun postIsEmailDuplicated(e: String) {
        viewModelScope.launch {
            runCatching {
                serverApi.postIsEmailDuplicated(EmailDuplicateCheckRequest(e))
            }.onSuccess {
                _isEmailValid.emit(it)
            }.onFailure {
                Log.d("test", it.toString())
            }
        }
    }
}