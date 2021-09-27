package com.haodanku.bianxian.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haodanku.bianxian.entry.Inner
import com.haodanku.bianxian.network.Api
import com.haodanku.bianxian.network.Network
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Author      : Stuart
 * Date        : 2021/6/24 17:32
 * Description :
 */
class ApiViewModel : ViewModel() {

    private val service by lazy { Network.create(Api::class.java) }

    val data = MutableLiveData<Inner>()

    fun getList() {
        viewModelScope.launch {
            kotlin.runCatching {
                service.getMainList("demo", "demo")
            }.onSuccess {
                withContext(Dispatchers.Main) {
                    if (it.code == 200) {
                        it.data?.apply { data.value = this }
                    }
                }
            }.onFailure {
                withContext(Dispatchers.Main) {
//                    val msg = "发生了错误, 详细：${it.message}\n点击重新加载"
                }
            }
        }
    }


}