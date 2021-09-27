package com.haodanku.bianxian.network

import com.haodanku.bianxian.entry.CustomData
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Author      : Stuart
 * Date        : 2021/9/18 17:08
 * Description :
 */
interface Api {

    @GET("sdk/tljActivity/getActivityList")
    suspend fun getMainList(
        @Query("app_secret") appSecret: String,
        @Query("app_key") appKey: String
    ): CustomData


}