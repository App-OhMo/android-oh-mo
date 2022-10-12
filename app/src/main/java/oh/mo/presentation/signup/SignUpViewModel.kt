package oh.mo.presentation.signup

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel() {

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
}