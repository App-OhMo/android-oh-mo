package oh.mo.presentation.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class SignInViewModel : ViewModel() {

    private val _isBtnEnabled = MutableSharedFlow<List<Boolean>>()
    val isBtnEnabled = _isBtnEnabled.asSharedFlow()

    fun setBtnEnabled(isEmailValid: Boolean, isPasswordValid: Boolean) {
        viewModelScope.launch {
            _isBtnEnabled.emit(listOf(isEmailValid, isPasswordValid))
        }
    }
}