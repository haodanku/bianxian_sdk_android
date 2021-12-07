package com.haodanku.bianxian.ui

import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.haodanku.bianxian.R
import com.haodanku.bianxian.adt.CustomListDelegate
import com.haodanku.bianxian.databinding.FragmentGridBinding
import com.haodanku.bianxian.databinding.FragmentModeluBinding
import com.haodanku.bianxian.dp
import com.haodanku.bianxian.ui.adapter.GoodsItemAdapter
import com.haodanku.bianxian.ui.adapter.ImageAdapter
import com.haodanku.sdk.Hdk
import com.haodanku.sdk.callback.HdkElementListener
import com.haodanku.sdk.entry.config.PorcelainConfig
import com.haodanku.sdk.entry.config.WorthBuyingTodayConfig

/**
 * @brief description
 * @author kent
 * @date 2021-11-25
 */
class ModuleFragment : Fragment(){

    private var _binding: FragmentModeluBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentModeluBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val content = ArrayList<String>()
        repeat(100){
            content.add("")
        }

        binding.goodsRy.apply {
           context?.let {
                layoutManager = LinearLayoutManager(context)
                adapter = GoodsItemAdapter(it,content)
            }
        }

        val config = WorthBuyingTodayConfig()
        config.setMargin(10,10,10,10)
        config.radius = 10f

        Hdk.loadElement(context,config,object :HdkElementListener{
            override fun onError(code: Int, error: String) {
                Log.e("ModuleFragment", "onError: $error")
            }

            override fun onSuccess(hdkElement: View) {
                binding.hdkWorthBuyContairll.addView(hdkElement)
            }

        })

        val porcelainConfig = PorcelainConfig()
        porcelainConfig.setMargin(10,10,10,10)
        porcelainConfig.proxyImage =  ImageAdapter(binding.hdkWorthBuyContairll.context)
        Hdk.loadElement(context,porcelainConfig,object :HdkElementListener{
            override fun onError(code: Int, error: String) {
                Log.e("ModuleFragment", "onError: $error")
            }

            override fun onSuccess(hdkElement: View) {
                binding.hdkWorthBuyContairll.addView(hdkElement)
            }

        })
    }
}