package com.haodanku.bianxian

import android.content.res.Resources

/**
 * Author      : Stuart
 * Date        : 2021/9/18 17:40
 * Description :
 */

val Int.dp: Int
    get() {
        val scale = Resources.getSystem().displayMetrics.density
        return (this * scale + 0.5f).toInt()
    }