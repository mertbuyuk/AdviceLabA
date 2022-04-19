package com.mb.advlab.di


import com.mb.advlab.BuildConfig
import com.mb.advlab.api.AdvLabService
import com.mb.advlab.api.RemoteDataSource
import com.mb.advlab.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton




@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient) : Retrofit =

        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit)  = retrofit.create(AdvLabService::class.java)

    @Provides
    fun provideOkHttpClient(): OkHttpClient {

        val builder = OkHttpClient.Builder()

        builder.interceptors().add(HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        })
        return builder.build()
    }

    @Provides
    fun provideRemoteDataSource(apiService: AdvLabService) : RemoteDataSource{
        return RemoteDataSource(apiService)
    }
}