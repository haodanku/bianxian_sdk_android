package com.haodanku.bianxian.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.haodanku.bianxian.databinding.ItemGoodsBinding

/**
 * @brief description
 * @author kent
 * @date 2021-11-26
 */
class GoodsItemAdapter(var context:Context,var content:List<String>) : RecyclerView.Adapter<GoodsItemAdapter.ViewHolder>() {
    class ViewHolder(viewItem:ItemGoodsBinding):RecyclerView.ViewHolder(viewItem.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemGoodsBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int = content.size
}