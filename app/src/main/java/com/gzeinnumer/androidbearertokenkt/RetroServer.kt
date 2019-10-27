package com.gzeinnumer.androidbearertokenkt

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class  RetroServer {
    companion object {
        fun RetroServerInit(): ApiService {

            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            // call token by preferences, look example in daron labs
            val httpClient = OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60L, TimeUnit.SECONDS)
                .writeTimeout(60L, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .addInterceptor {
                    val original = it.request()
                    val builder = original.newBuilder()
                    builder.addHeader("Accept", "application/json")
                    builder.addHeader("Authorization", "Bearer Token")
                    val request = builder.build()
                    return@addInterceptor it.proceed(request)
                }
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl("http://192.168.1.2:8000/api/")
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}