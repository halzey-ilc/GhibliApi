package com.example.ghibliapi.di

import com.example.ghibliapi.data.network.GhibiApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent :: class)
object NetworkModule {

    @Provides
    fun provideRetrofit(): Retrofit {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        val clientLoggin: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        return Retrofit.Builder()
            .baseUrl("https://ghibliapi.herokuapp.com/")
            .client(clientLoggin)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        @Provides
        fun provideGlibhiAPI(retrofit: Retrofit): GhibiApi {
            return retrofit.create(GhibiApi::class.java)
        }
    }
}