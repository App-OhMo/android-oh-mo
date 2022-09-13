package oh.mo.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import oh.mo.data.api.ServiceApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class ServerRetrofit

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class ApiRetrofit

    private const val BASE_URL = "http://3.38.56.88:8080"

    @Provides
    @Singleton
    @ServerRetrofit
    fun provideServerRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpLoggingClient())
            .build()
    }

    private fun httpLoggingClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    /**
     *  해당 부분은 api method (GET, POST .. ) 생성 후 구현
     *  각자 맡은 부분의 method 는 다른 파일에 관리해주세요.
     *  ex. LoginService / SearchService / BoardService / ProfileService ...
     * **/
    /*
    @Provides
    @Singleton
    fun providerService(retrofit: Retrofit): RetrofitAPI {
        return retrofit.create(RetrofitAPI::class.java)
    }
     */

    @Provides
    @ApiRetrofit
    fun provideBASEURL() = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/"

    @Provides
    @Singleton
    @ApiRetrofit
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    @Provides
    @Singleton
    @ApiRetrofit
    fun provideApiRetrofit(@ApiRetrofit okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .baseUrl(provideBASEURL())
            .build()
    }

    @Provides
    @Singleton
    @ApiRetrofit
    fun provideServiceApi(@ApiRetrofit retrofit: Retrofit): ServiceApi {
        return retrofit.create(ServiceApi::class.java)
    }
}