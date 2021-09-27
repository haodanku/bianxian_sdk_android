package com.haodanku.bianxian.adt

import android.content.Context
import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.ScreenUtils
import com.bumptech.glide.Glide
import com.drakeet.multitype.ItemViewDelegate
import com.drakeet.multitype.MultiTypeAdapter
import com.haodanku.bianxian.databinding.ItemCustomBinding
import com.haodanku.bianxian.dp
import com.haodanku.bianxian.entry.ItemData
import com.haodanku.bianxian.entry.Lists
import com.haodanku.sdk.Hdk

/**
 * Author      : Stuart
 * Date        : 2021/9/18 17:17
 * Description :
 */
class CustomListDelegate : ItemViewDelegate<Lists, CustomListDelegate.ViewHolder>() {

    class ViewHolder(val binding: ItemCustomBinding) : RecyclerView.ViewHolder(binding.root) {

        private val adt = MultiTypeAdapter()
        private val items = ArrayList<Any>()
        private val screenWidth = ScreenUtils.getScreenWidth()

        init {

            val params = binding.image.layoutParams
            val w = screenWidth - 16.dp
            params.width = w
            params.height = 280 * w / 750
            binding.image.layoutParams = params

            adt.register(CustomListInnerDelegate())
            adt.items = items
            binding.recyclerView.apply {
                adapter = adt
                layoutManager =
                    LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
                addItemDecoration(object : RecyclerView.ItemDecoration() {
                    override fun getItemOffsets(
                        outRect: Rect,
                        view: View,
                        parent: RecyclerView,
                        state: RecyclerView.State
                    ) {
                        val pos = parent.getChildAdapterPosition(view)
                        if (pos == 0) {
                            outRect.set(8.dp, 8.dp, 8.dp, 8.dp)
                        } else {
                            outRect.set(0.dp, 8.dp, 8.dp, 8.dp)
                        }
                    }
                })
            }
        }

        fun setInnerList(list: List<ItemData>?) {
            if (!list.isNullOrEmpty()) {
                items.clear()
                items.addAll(list)
                adt.notifyItemRangeChanged(0, items.size)
            }
        }

    }

    override fun onBindViewHolder(holder: ViewHolder, item: Lists) {
        holder.binding.title.text = item.title
        Glide.with(holder.binding.image).load(item.img).into(holder.binding.image)
        holder.setInnerList(item.items)
        holder.itemView.setOnClickListener {
            Hdk.jumpPage(item.jump)
        }
    }

    override fun onCreateViewHolder(context: Context, parent: ViewGroup): ViewHolder {
        val binding = ItemCustomBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

}