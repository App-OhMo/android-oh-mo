package oh.mo.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import oh.mo.data.api.ShortWeatherApi
import oh.mo.data.model.remote.response.ShortTermForecastResponse
import oh.mo.di.NetworkModule
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    @NetworkModule.ShortWeatherApiRetrofit private val shortWeatherApi: ShortWeatherApi,
) : ViewModel() {

    private val _list =
        MutableSharedFlow<List<ShortTermForecastResponse.Response.Body.Items.Item?>?>()
    val list = _list.asSharedFlow()

    fun getApi(base_date: String) {
        viewModelScope.launch {
            runCatching {
                shortWeatherApi.getShortTermForecastResponse(
                    num_of_rows = 290,
                    page_no = 1,
                    data_type = "JSON",
                    base_date = base_date,
                    base_time = "2300",
                    nx = 55,
                    ny = 127
                )
            }.onSuccess {
                val value = it.response?.body?.items?.item
                _list.emit(value)
            }.onFailure {
                delay(5000L)
                getApi(base_date)
            }
        }
    }
}