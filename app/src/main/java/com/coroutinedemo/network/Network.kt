package com.coroutinedemo.network

import com.coroutinedemo.BuildConfig
import com.coroutinedemo.common.Config
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by shivanggoel on 19,February,2020
 */
object Network {

    private val httpLoggingInterceptor = HttpLoggingInterceptor()

    init {
        if (BuildConfig.DEBUG)
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        else httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
    }

    /**
     * Retrofit client
     */
    private val retrofit = Retrofit.Builder()
        .baseUrl(Config.BASE_URL)
        .client(OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getNetworkClient(): Retrofit {
        return retrofit
    }
}