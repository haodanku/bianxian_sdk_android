package com.haodanku.bianxian

import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.Window
import android.view.WindowManager

/**
 * Author      : Stuart
 * Date        : 2021/8/25 17:02
 * Description :
 */
object Util {

    fun setStatusBarColor(activity: Activity, colorId: Int) {
        val window = activity.window
        window.statusBarColor = colorId
    }

    fun StatusBarLightMode(activity: Activity): Int {
        var result = 0
        if (MIUISetStatusBarLightMode(activity.window, true)) {
            result = 1
        } else if (FlymeSetStatusBarLightMode(activity.window, true)) {
            result = 2
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            result = 3
        } else {
            setStatusBarColor(activity, Color.TRANSPARENT)
        }
        return result
    }

    private fun MIUISetStatusBarLightMode(window: Window?, dark: Boolean): Boolean {
        var result = false
        if (window != null) {
            try {
                val lp = window.attributes
                val darkFlag = WindowManager.LayoutParams::class.java
                    .getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON")
                val meizuFlags = WindowManager.LayoutParams::class.java
                    .getDeclaredField("meizuFlags")
                darkFlag.isAccessible = true
                meizuFlags.isAccessible = true
                val bit = darkFlag.getInt(null)
                var value = meizuFlags.getInt(lp)
                value = if (dark) {
                    value or bit
                } else {
                    value and bit.inv()
                }
                meizuFlags.setInt(lp, value)
                window.attributes = lp
                result = true
            } catch (e: Exception) {
            }
        }
        return result
    }

    private fun FlymeSetStatusBarLightMode(window: Window?, dark: Boolean): Boolean {
        var result = false
        if (window != null) {
            try {
                val lp = window.attributes
                val darkFlag = WindowManager.LayoutParams::class.java
                    .getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON")
                val meizuFlags = WindowManager.LayoutParams::class.java
                    .getDeclaredField("meizuFlags")
                darkFlag.isAccessible = true
                meizuFlags.isAccessible = true
                val bit = darkFlag.getInt(null)
                var value = meizuFlags.getInt(lp)
                value = if (dark) {
                    value or bit
                } else {
                    value and bit.inv()
                }
                meizuFlags.setInt(lp, value)
                window.attributes = lp
                result = true
            } catch (e: Exception) {
            }
        }
        return result
    }
}