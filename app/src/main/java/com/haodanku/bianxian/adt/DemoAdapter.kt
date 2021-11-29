package com.haodanku.bianxian.adt

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.haodanku.bianxian.ui.DemoFragment
import com.haodanku.bianxian.ui.ApiFragment
import com.haodanku.bianxian.ui.GridFragment
import com.haodanku.bianxian.ui.ModuleFragment
import com.haodanku.sdk.Hdk
import com.haodanku.sdk.entry.SinglePage

/**
 * Author      : Stuart
 * Date        : 2021/9/25 10:42
 * Description :
 */
class DemoAdapter(aty: FragmentActivity) : FragmentStateAdapter(aty) {

    override fun getItemCount(): Int = 5

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> DemoFragment()
            1 -> Hdk.getSinglePage(SinglePage.MAIN) ?: DemoFragment()
            2 -> ApiFragment()
            3 -> GridFragment()
            4 -> ModuleFragment()
            else -> DemoFragment()
        }
    }



}