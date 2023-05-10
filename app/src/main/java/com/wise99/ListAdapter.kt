package com.wise99

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.wise99.primrose_kotlin.Flower
import com.wise99.primrose_kotlin.GlideApp
import com.wise99.primrose_kotlin.R


class ListAdapter(private val context: Context): RecyclerView.Adapter<ListAdapter.ViewHolder>() {
    private var flowerList = mutableListOf<Flower>()

    fun setListData(data:MutableList<Flower>){
        flowerList = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recyclerview,parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListAdapter.ViewHolder, position: Int) {
        val flower : Flower = flowerList[position]
        val storageReference = Firebase.storage.reference.child("/"+flower.image)
        holder.name.text = flower.fname
        holder.mean.text = flower.floriography
        println(flower.image)
        GlideApp.with(context).load(storageReference).into(holder.img)

    }

    override fun getItemCount(): Int {
        return flowerList.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val name : TextView = itemView.findViewById(R.id.tv_name)
        val mean : TextView = itemView.findViewById(R.id.tv_mean)
        val img : ImageView = itemView.findViewById(R.id.iv_photo)

    }
}