package com.wise99.primrose_kotlin.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.wise99.primrose_kotlin.MainActivity
import com.wise99.primrose_kotlin.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    // 1. 뷰 바인딩 설정
    // xml파일명의 첫글자와 언더바_ 다음 글자를 대문자로 바꾸고 Binding을 붙여주면 됨
    lateinit var binding: FragmentMainBinding

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false) // 사용할 레이아웃, 인수(부모), 프레그먼트 자동으로 추가할 것 인지
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}