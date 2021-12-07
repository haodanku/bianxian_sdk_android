package com.haodanku.bianxian

import android.app.Application
import android.util.Log
import android.widget.Toast
import com.haodanku.sdk.Hdk
import com.haodanku.sdk.entry.HdkAgentInfo

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
        Hdk.init(this, "joyce", object : Hdk.InitCallback {
            override fun onResult(code: Int, message: String) {
                Log.d("MyApp", "code:$code, msg:$message")
                val agent = HdkAgentInfo()
                agent.userId = "demo"
                Hdk.setAgent(agent)
            }
        })
    }

}