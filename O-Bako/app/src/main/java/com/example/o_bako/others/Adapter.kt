package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.o_bako.R

class Adapter (DataBarang : MutableList<Data>) : RecyclerView.Adapter<Adapter.Holder>(){
    private val dataStock = DataBarang
    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val ImageBarang = itemView.findViewById<ImageView>(R.id.imgBarang)
        val NamaBarang = itemView.findViewById<TextView>(R.id.namaBarang)
        val DeskripsiBarang = itemView.findViewById<TextView>(R.id.deskripsiBarang)
        val Quantity = itemView.findViewById<TextView>(R.id.quantity)
        val Plus = itemView.findViewById<Button>(R.id.plus)
        val Minus = itemView.findViewById<Button>(R.id.minus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflate = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_view_recycle,parent,false)
        return Holder(inflate)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.ImageBarang.setImageResource(dataStock.get(position).Image)
        holder.NamaBarang.setText(dataStock.get(position).Nama)
        holder.DeskripsiBarang.setText(dataStock.get(position).Deskripsi)
        holder.Quantity.setText(dataStock.get(position).Qty)
    }

    override fun getItemCount(): Int = dataStock.size
}