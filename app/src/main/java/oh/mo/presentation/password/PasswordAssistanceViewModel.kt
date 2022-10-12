package oh.mo.presentation.password

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class PasswordAssistanceViewModel : ViewModel() {

    private val _isBtnEnabled = MutableSharedFlow<List<Boolean>>()
    val isBtnEnabled = _isBtnEnabled.asSharedFlow()

    fun setBtnEnabled(isNickNameValid: Boolean, isEmailValid: Boolean) {
        viewModelScope.launch {
            _isBtnEnabled.emit(listOf(isNickNameValid, isEmailValid))
        }
    }
}