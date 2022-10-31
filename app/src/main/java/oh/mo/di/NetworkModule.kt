package oh.mo.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import oh.mo.data.api.ServerApi
import oh.mo.data.api.ShortWeatherApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    /**
     *  해당 부분은 api method (GET, POST .. ) 생성 후 구현
     *  각자 맡은 부분의 method 는 다른 파일에 관리해주세요.
     *  ex. LoginService / SearchService / BoardService / ProfileService ...
     * **/

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class ServerRetrofit

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class ShortWeatherApiRetrofit

    private const val SERVER_BASE_URL = "http://3.38.56.88:8080/"
    private const val SHORT_WEATHER_BASE_URL =
        "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/"

    private fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    // --- Server --- //

    @Provides
    @Singleton
    @ServerRetrofit
    fun provideServerRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(SERVER_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideOkHttpClient())
            .build()
    }

    @Provides
    @Singleton
    @ServerRetrofit
    fun providerServerApi(@ServerRetrofit retrofit: Retrofit): ServerApi {
        return retrofit.create(ServerApi::class.java)
    }

    // --- Short Weather --- //

    @Provides
    @Singleton
    @ShortWeatherApiRetrofit
    fun provideShortWeatherApiRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(SHORT_WEATHER_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideOkHttpClient())
            .build()
    }

    @Provides
    @Singleton
    @ShortWeatherApiRetrofit
    fun provideShortWeatherApi(@ShortWeatherApiRetrofit retrofit: Retrofit): ShortWeatherApi {
        return retrofit.create(ShortWeatherApi::class.java)
    }
}