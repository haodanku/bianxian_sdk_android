package com.haodanku.bianxian.adt

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.drakeet.multitype.ItemViewDelegate
import com.haodanku.bianxian.databinding.ItemInnerCustomBinding
import com.haodanku.bianxian.entry.ItemData

/**
 * Author      : Stuart
 * Date        : 2021/9/22 11:09
 * Description :
 */
class CustomListInnerDelegate : ItemViewDelegate<ItemData, CustomListInnerDelegate.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, item: ItemData) {
        item.itempic?.apply {
            val img = if (!startsWith("http")) { "http:${this}" } else { this }
            Glide.with(holder.binding.image).load(img).into(holder.binding.image)
        }
        holder.binding.title.text = item.itemshorttitle ?: "（无标题）"
        holder.binding.price.text = "¥${item.itemendprice}"
        holder.binding.sale.text = "已售${item.itemsale}"
    }

    override fun onCreateViewHolder(context: Context, parent: ViewGroup): ViewHolder {
        val binding = ItemInnerCustomBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    class ViewHolder(val binding: ItemInnerCustomBinding) : RecyclerView.ViewHolder(binding.root)

}