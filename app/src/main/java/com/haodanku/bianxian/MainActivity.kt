package com.haodanku.bianxian

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.haodanku.bianxian.adt.DemoAdapter
import com.haodanku.sdk.Hdk
import com.haodanku.sdk.entry.SinglePage

class MainActivity : AppCompatActivity() {

    private lateinit var demoAdapter: DemoAdapter
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Util.setStatusBarColor(this, Color.WHITE)
        Util.StatusBarLightMode(this)
        setContentView(R.layout.activity_main)

        tabLayout = findViewById(R.id.tab_layout)
        viewPager = findViewById(R.id.pager)
        viewPager.isUserInputEnabled = false
        demoAdapter = DemoAdapter(this)
        viewPager.adapter = demoAdapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "首页"
                1 -> "嵌入"
                2 -> "API"
                3 -> "模块"
                4 -> "列表控件"
                else -> "tab"
            }
        }.attach()

    }

}