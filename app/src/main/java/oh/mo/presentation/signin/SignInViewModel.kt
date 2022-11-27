package oh.mo.presentation.signin

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import oh.mo.data.api.ServerApi
import oh.mo.data.model.remote.request.SignInRequest
import oh.mo.di.NetworkModule
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    @NetworkModule.ServerRetrofit private val serverApi: ServerApi,
) : ViewModel() {

    private val _isBtnEnabled = MutableSharedFlow<List<Boolean>>()
    val isBtnEnabled = _isBtnEnabled.asSharedFlow()

    fun setBtnEnabled(isEmailValid: Boolean, isPasswordValid: Boolean) {
        viewModelScope.launch {
            _isBtnEnabled.emit(listOf(isEmailValid, isPasswordValid))
        }
    }

    fun postUserSignIn(
        email: String,
        password: String,
        toastMsg: (String) -> Unit,
        saveToken: (String) -> Unit,
        exit: () -> Unit,
    ) {
        viewModelScope.launch {
            runCatching {
                serverApi.postUserSignIn(
                    data = SignInRequest(
                        email,
                        password
                    )
                )
            }.onSuccess {
                toastMsg(it.responseMessage)
                saveToken(it.data ?: "")
                exit()
            }.onFailure {
                toastMsg("로그인 실패")
                Log.d("userSignIn Failed", it.toString())
            }
        }
    }
}