package com.haodanku.bianxian

import android.app.Application
import android.util.Log
import com.haodanku.sdk.Hdk

/**
 * Author      : Stuart
 * Date        : 2021/8/16 11:40
 * Description :
 */
class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        //是否显示日志
        Hdk.setDebug(BuildConfig.DEBUG)
        // 请将以下的 appKey 和 appSecret 更换为在好单库变现平台正式申请的
        Hdk.init(this, "demo", "demo", object : Hdk.InitCallback {
            override fun onResult(code: Int, message: String) {
                Log.e("MyApp", "code:$code, msg:$message")
            }
        })
    }

}