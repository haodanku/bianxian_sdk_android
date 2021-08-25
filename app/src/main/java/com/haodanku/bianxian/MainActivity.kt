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
            tab.text = "Tab ${(position + 1)}"
        }.attach()

    }

    class DemoAdapter(aty: FragmentActivity) : FragmentStateAdapter(aty) {

        override fun getItemCount(): Int = 4

        override fun createFragment(position: Int): Fragment {
            return if (position == 1) {
                Hdk.getSinglePage(SinglePage.MAIN) ?: DemoFragment()
            } else {
                DemoFragment()
            }
        }
    }
}