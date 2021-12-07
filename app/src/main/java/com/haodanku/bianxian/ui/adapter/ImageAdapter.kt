package com.haodanku.bianxian.ui.adapter

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.haodanku.sdk.callback.HdkImageProxy

/**
 * @brief description
 * @author kent
 * @date 2021-12-07
 */
class ImageAdapter(val context:Context?):HdkImageProxy {
    override fun loadImageUrl(view: ImageView?, url: String?) {
        view?.let {
            if (context != null) {
                Glide.with(context)
                    .load(url)
                    .into(it)
            }
        }
    }
}