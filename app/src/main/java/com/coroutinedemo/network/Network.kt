package com.coroutinedemo.network

import com.coroutinedemo.common.Config
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by shivanggoel on 19,February,2020
 */
object Network {

    /**
     * Retrofit client
     */
    private val retrofit = Retrofit.Builder()
        .baseUrl(Config.BASE_URL)
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getNetworkClient(): Retrofit{
        return retrofit
    }
}