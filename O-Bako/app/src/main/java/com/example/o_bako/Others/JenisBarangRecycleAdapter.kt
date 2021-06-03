package com.example.o_bako

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.o_bako.DatabaseSQLite.Data

class JenisBarangRecycleAdapter (DataBarang : MutableList<Data>) :
        RecyclerView.Adapter<JenisBarangRecycleAdapter.Holder>(){
    private val dataStock = DataBarang

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val NamaBarang = itemView.findViewById<TextView>(R.id.namaBarang)
        val DeskripsiBarang = itemView.findViewById<TextView>(R.id.deskripsiBarang)
        val Quantity = itemView.findViewById<TextView>(R.id.quantity)
        val Cost = itemView.findViewById<TextView>(R.id.harga_barang)
        val Plus = itemView.findViewById<Button>(R.id.plus)
        val Minus = itemView.findViewById<Button>(R.id.minus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflate = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_view_recycle,parent,false)
        return Holder(inflate)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.NamaBarang.setText(dataStock.get(position).Nama)
        holder.DeskripsiBarang.setText(dataStock.get(position).Deskripsi)
        holder.Quantity.setText(dataStock.get(position).Qty)
        holder.Cost.setText(dataStock.get(position).Harga_Barang)

    }
    override fun getItemCount(): Int = dataStock.size
}