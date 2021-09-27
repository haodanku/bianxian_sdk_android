package com.haodanku.bianxian.ui

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.drakeet.multitype.MultiTypeAdapter
import com.haodanku.bianxian.R
import com.haodanku.bianxian.adt.CustomListDelegate
import com.haodanku.bianxian.databinding.FragmentApiBinding
import com.haodanku.bianxian.dp
import com.haodanku.bianxian.vm.ApiViewModel


/**
 * Author      : Stuart
 * Date        : 2021/8/20 15:45
 * Description :
 */
class ApiFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    private val viewModel by viewModels<ApiViewModel>()

    private var _binding: FragmentApiBinding? = null
    private val binding get() = _binding!!

    private val listAdapter = MultiTypeAdapter()
    private val items = ArrayList<Any>()

    private var init = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentApiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.refreshLayout.setOnRefreshListener(this)
        binding.refreshLayout.setColorSchemeResources(R.color.purple_200)

        listAdapter.register(CustomListDelegate())
        listAdapter.items = items

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = listAdapter
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
                        outRect.set(8.dp, 0, 8.dp, 8.dp)
                    }
                }
            })
        }

        viewModel.data.observe(viewLifecycleOwner) {
            if (!init) {
                binding.loadingView.visibility = View.GONE
                init = true
            } else {
                binding.refreshLayout.isRefreshing = false
            }
            items.clear()
            if (!it.lists.isNullOrEmpty()) {
                items.addAll(it.lists)
            }else {
                Toast.makeText(requireContext(), "当前无活动", Toast.LENGTH_SHORT).show()
            }
            listAdapter.notifyItemRangeChanged(0, items.size)
        }

    }

    override fun onResume() {
        super.onResume()
        if (!init) {
            binding.loadingView.visibility = View.VISIBLE
            viewModel.getList()
        }
    }

    override fun onRefresh() {
        binding.refreshLayout.isRefreshing = true
        viewModel.getList()
    }

}