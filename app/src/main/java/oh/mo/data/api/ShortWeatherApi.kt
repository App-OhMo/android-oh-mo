package oh.mo.data.api

import oh.mo.data.model.remote.response.ShortTermForecastResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ShortWeatherApi {

    @GET("getVilageFcst?serviceKey=0ElkboaXwY1%2FJ2p4im01RxZ8Nh2%2Bt2E%2FQuim2Ebl0g9KNQsNy6i3YMq4xWjl1%2FmEqFEOOjHQvk5Nu0xZv5%2FJCg%3D%3D")
    suspend fun getShortTermForecastResponse(
        @Query("numOfRows") num_of_rows: Int,
        @Query("pageNo") page_no: Int,
        @Query("dataType") data_type: String,
        @Query("base_date") base_date: String,
        @Query("base_time") base_time: String,
        @Query("nx") nx: Int,
        @Query("ny") ny: Int,
    ): ShortTermForecastResponse
}