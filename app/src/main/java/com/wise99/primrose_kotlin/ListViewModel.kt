package com.wise99.primrose_kotlin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

// Repository에 있는 데이터를 관찰하고 있다가 변경이 되면 mutableData의 값을 변경
class ListViewModel : ViewModel() {
    private val repo = Repository()
    fun fetchData(): LiveData<MutableList<Flower>> {
        val mutableData = MutableLiveData<MutableList<Flower>>()
        repo.getData().observeForever{
            mutableData.value = it
        }
        return mutableData
    }
}