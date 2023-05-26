package com.wise99.primrose_kotlin.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wise99.primrose_kotlin.ListAdapter
import com.wise99.primrose_kotlin.ListViewModel
import com.wise99.primrose_kotlin.MainActivity
import com.wise99.primrose_kotlin.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {
    lateinit var binding: FragmentSearchBinding
    private lateinit var adapter: ListAdapter
    private val viewModel by lazy { ViewModelProvider(this)[ListViewModel::class.java] }

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
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val etSearchMean = binding.MeanSearchET
        val btnSearchMean = binding.searchMeanBtn

        adapter = ListAdapter(mainContext)

        val recyclerViewMean : RecyclerView = binding.recyclerViewMean
        recyclerViewMean.layoutManager = LinearLayoutManager(mainContext)
        recyclerViewMean.adapter = adapter

        btnSearchMean.setOnClickListener {
            // 검색창에 입력한 단어
            val searchMean = etSearchMean.text.toString()
            observerData(searchMean)
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    fun observerData(words:String) {
        viewModel.searchData(words).observe(viewLifecycleOwner, Observer {
            adapter.setListData(it)
            adapter.notifyDataSetChanged()
        })
    }
}