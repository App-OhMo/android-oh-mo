package oh.mo.presentation.splash

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import oh.mo.data.api.ServerApi
import oh.mo.data.model.remote.request.SignInRequest
import oh.mo.data.model.remote.response.ShortTermForecastResponse
import oh.mo.di.NetworkModule
import oh.mo.utils.SharedPrefUtil
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    @NetworkModule.ServerRetrofit private val serverApi: ServerApi,
) : ViewModel() {

    private val _isTokenValid = MutableSharedFlow<Boolean>()
    val isTokenValid = _isTokenValid.asSharedFlow()

    fun postCheckToken() {
        viewModelScope.launch {
            runCatching {
                serverApi.postCheckToken(
                    SharedPrefUtil.getAccessToken(),
                    cookie = "X-AUTH-TOKEN=" + SharedPrefUtil.getAccessToken()
                )
            }.onSuccess {
                _isTokenValid.emit(it)
            }.onFailure {
                _isTokenValid.emit(false)
                Log.d("isTokenValid", "token err")
            }
        }
    }
}