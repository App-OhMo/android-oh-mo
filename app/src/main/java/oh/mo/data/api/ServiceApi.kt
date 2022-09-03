package oh.mo.data.api

import oh.mo.data.model.ShortTermForecastResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceApi {

    @GET("getVilageFcst?serviceKey=0ElkboaXwY1%2FJ2p4im01RxZ8Nh2%2Bt2E%2FQuim2Ebl0g9KNQsNy6i3YMq4xWjl1%2FmEqFEOOjHQvk5Nu0xZv5%2FJCg%3D%3D")

    suspend fun getWeather(
        @Query("numOfRows") num_of_rows: Int,   // 한 페이지 결과 수
        @Query("pageNo") page_no: Int,          // 페이지 번호
        @Query("dataType") data_type: String,   // 응답 자료 형식
        @Query("base_date") base_date: String,  // 발표 일자
        @Query("base_time") base_time: String,  // 발표 시각
        @Query("nx") nx: Int,                // 예보지점 X 좌표
        @Query("ny") ny: Int,                // 예보지점 Y 좌표
    ): ShortTermForecastResponse

}