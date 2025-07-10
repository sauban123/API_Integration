package com.example.api_intergation_in_android

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso

class MyAdapter(val context : Activity , val productArrayList : List<Product>) :   RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.eachitem,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = productArrayList[position]
        holder.tittle.text = currentItem.title
        // image view , how to show image in image view if the image is in form of url,
       Picasso.get().load(currentItem.thumbnail).into(holder.Image)

    }

    override fun getItemCount(): Int {
        return productArrayList.size
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tittle = itemView.findViewById<TextView>(R.id.productName)
        val Image = itemView.findViewById<ShapeableImageView>(R.id.productImage)
        // initialization of listener
        init {

        }

    }

}