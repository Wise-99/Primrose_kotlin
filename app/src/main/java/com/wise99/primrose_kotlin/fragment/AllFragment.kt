package com.wise99.primrose_kotlin.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wise99.ListAdapter
import com.wise99.primrose_kotlin.ListViewModel
import com.wise99.primrose_kotlin.MainActivity
import com.wise99.primrose_kotlin.databinding.FragmentAllBinding

class AllFragment : Fragment() {
    lateinit var binding: FragmentAllBinding
    private lateinit var adapter: ListAdapter
    private val viewModel by lazy { ViewModelProvider(this).get(ListViewModel::class.java) }

    // 1. Context를 할당할 변수를 프로퍼티로 선언(어디서든 사용할 수 있게)
    lateinit var mainContext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // 2. Context를 액티비티로 형변환해서 할당
        mainContext = context
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        // 사용할 레이아웃, 인수(부모), 프레그먼트 자동으로 추가할 것 인지
        binding = FragmentAllBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ListAdapter(mainContext)

        val recyclerView : RecyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(mainContext)
        recyclerView.adapter = adapter
        observerData()
    }

    fun observerData() {
        viewModel.fetchData().observe(viewLifecycleOwner, Observer {
            adapter.setListData(it)
            adapter.notifyDataSetChanged()
        })
    }
}