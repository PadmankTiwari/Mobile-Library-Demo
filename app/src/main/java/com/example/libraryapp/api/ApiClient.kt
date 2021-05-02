package com.example.libraryapp.api

import com.example.libraryapp.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient {
    companion object {

        var retrofit: Retrofit? = null
            get() {
                field = field ?: Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                    .baseUrl(ApiConstants.BASE_URL)
                    .client(client!!)
                    .build()
                return field
            }

        private var interceptor = HttpLoggingInterceptor()
            get() {
                if (BuildConfig.DEBUG)
                    field.level = HttpLoggingInterceptor.Level.BODY
                else
                    field.level = HttpLoggingInterceptor.Level.NONE
                return field
            }

        private var client: OkHttpClient? = null
            get() {
                val dispatcher = Dispatcher()
                dispatcher.maxRequests = 1
                field = field ?: OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .dispatcher(dispatcher)
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .build()
                return field
            }

    }
}