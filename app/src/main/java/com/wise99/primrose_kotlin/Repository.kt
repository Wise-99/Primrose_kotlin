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
    fun getData(): LiveData<MutableList<Flower>> {
        val mutableData = MutableLiveData<MutableList<Flower>>()
        val database = Firebase.database
        val myRef = database.getReference("Flower")

        myRef.addValueEventListener(object : ValueEventListener {
            val listData: MutableList<Flower> = mutableListOf<Flower>()

            override fun onDataChange(snapshot: DataSnapshot) {
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
}