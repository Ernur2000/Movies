package com.example.moviesapp.di

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.moviesapp.data.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun baseUrl() = "https://api.tvmaze.com/"

    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY  // Logs request and response body
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        @ApplicationContext context: Context // Inject Application Context here
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(ChuckerInterceptor(context)) // Use the context here
            .build()
    }

    @Provides
    @Singleton
    fun providerRetrofit(baseUrl: String, okHttpClient: OkHttpClient) : ApiService =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
}