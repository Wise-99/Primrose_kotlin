package com.wise99.primrose_kotlin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

// datasource를 캡슐화 하는 것
class Repository {
    val mutableData = MutableLiveData<MutableList<Flower>>()
    val database = Firebase.database
    val myRef = database.getReference("Flower")
    val listData: MutableList<Flower> = mutableListOf<Flower>()

    fun getData(): LiveData<MutableList<Flower>> {
        myRef.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                // 리스트 데이터 초기화
                listData.clear()

                if (snapshot.exists()){
                    for (userSnapshot in snapshot.children){
                        val getData = userSnapshot.getValue(Flower::class.java)
                        listData.add(getData!!)

                        mutableData.value = listData
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {

            }
        })
        return mutableData
    }

    fun searchData(words : String) : LiveData<MutableList<Flower>> {

        myRef.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                listData.clear()

                if (snapshot.exists()){
                    for (userSnapshot in snapshot.children){
                        val getData = userSnapshot.getValue(Flower::class.java)
                        if (getData?.floriography?.contains(words) == true || getData?.fname?.contains(words) == true)
                            listData.add(getData!!)

                        mutableData.value = listData
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {

            }
        })
        return mutableData
    }
}