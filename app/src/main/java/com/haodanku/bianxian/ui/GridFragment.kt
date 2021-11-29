package com.haodanku.bianxian.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.haodanku.bianxian.databinding.FragmentGridBinding
import com.haodanku.sdk.Hdk
import com.haodanku.sdk.entry.ModulePage
import com.haodanku.sdk.entry.NavPage

/**
 * Author      : Stuart
 * Date        : 2021/9/23 10:28
 * Description :
 */
class GridFragment : Fragment() {

    private var _binding: FragmentGridBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGridBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnJd.setOnClickListener {
            Hdk.jumpNav(NavPage.JD)
        }
        binding.btnPdd.setOnClickListener {
            Hdk.jumpNav(NavPage.PDD)
        }
        binding.btnVip.setOnClickListener {
            Hdk.jumpNav(NavPage.VIP)
        }
        binding.btnMt.setOnClickListener {
            Hdk.jumpNav(NavPage.MEI_TUAN)
        }
        binding.btnPhoneBill.setOnClickListener {
            Hdk.jumpNav(NavPage.PHONE_BILL)
        }
        binding.btnXianBao.setOnClickListener {
            Hdk.jumpNav(NavPage.XIAN_BAO)
        }
        binding.btnArea.setOnClickListener {
            Hdk.jumpNav(NavPage.EPIDEMIC_AREA)
        }
        binding.btnKfc.setOnClickListener {
            Hdk.jumpNav(NavPage.KFC)
        }
        binding.btnMovie.setOnClickListener {
            Hdk.jumpNav(NavPage.MOVIE)
        }
        binding.btnEle.setOnClickListener {
            Hdk.jumpNav(NavPage.ELE)
        }
        binding.btnWallet.setOnClickListener {
            val params = HashMap<String,String>()
            params["token"] ="KhUI7Pp2aWgKKL9L"
            Hdk.jumpModule(ModulePage.WALLET,params)
        }
    }

}