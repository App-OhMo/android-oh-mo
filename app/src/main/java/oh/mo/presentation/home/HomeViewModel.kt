package oh.mo.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import oh.mo.data.api.ServiceApi
import oh.mo.data.model.ShortTermForecastResponse
import oh.mo.di.NetworkModule
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    @NetworkModule.ApiRetrofit private val serviceApi: ServiceApi,
) : ViewModel() {

    private val _list =
        MutableSharedFlow<List<ShortTermForecastResponse.Response.Body.Items.Item?>?>()
    val list = _list.asSharedFlow()

    fun getApi() {
        viewModelScope.launch {
            Log.d("viewModel", serviceApi.toString())

            val response = serviceApi.getShortTermForecastResponse(
                10,
                1,
                "JSON",
                "20220903",
                "1700",
                55,
                127
            )

            val value = response.response?.body?.items?.item

            Log.d("viewModel", value.toString())
            _list.emit(value)
        }
    }
}