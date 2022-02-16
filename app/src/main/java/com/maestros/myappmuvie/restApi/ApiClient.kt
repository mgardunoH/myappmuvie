package com.maestros.myappmuvie.restApi

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {
    private val gson = GsonBuilder()
        .setDateFormat("yyyy/MM/dd HH:mm:ss")
        .create()

    val client: Retrofit
        get() {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .callTimeout(90, TimeUnit.SECONDS)
                .connectTimeout(90, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS)
                .build()
            return Retrofit.Builder()
                .baseUrl("https://reqres.in/api")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build()
        }
}