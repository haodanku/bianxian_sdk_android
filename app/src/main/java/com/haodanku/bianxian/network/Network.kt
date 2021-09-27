package com.haodanku.bianxian.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Author      : Stuart
 * Date        : 2021/9/18 17:07
 * Description :
 */
object Network {

    const val BASE_URL = "https://api.bianxian.haodanku.com/"

    private var retrofit: Retrofit

    init {

        val client = OkHttpClient.Builder()
            .connectTimeout(8, TimeUnit.SECONDS)
            .writeTimeout(8, TimeUnit.SECONDS)
            .readTimeout(8, TimeUnit.SECONDS)
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    fun <T> create(tClass: Class<T>?): T {
        return retrofit.create(tClass)
    }

}