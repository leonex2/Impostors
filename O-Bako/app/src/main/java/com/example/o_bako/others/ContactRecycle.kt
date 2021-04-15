package com.example.o_bako.others

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.o_bako.R
import kotlinx.android.synthetic.main.recycle_contact_list.view.*

class ContactRecycle (private val myContact : List<ContactList>) : RecyclerView.Adapter<Holder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflate = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycle_contact_list,parent,false)
        return Holder(inflate)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bindContact(myContact[position])
        holder.statusDisplay.isChecked = false
    }

    override fun getItemCount(): Int = myContact.size
}
class Holder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val namaDisplay = itemView.display_name
    val numberDisplay = itemView.display_number
    val statusDisplay = itemView.checkList

    fun bindContact(temp : ContactList) {
        namaDisplay.text = "${namaDisplay.text} : ${temp.nama}"
        numberDisplay.text = "${numberDisplay.text} : ${temp.noHP}"
        statusDisplay.isChecked = false
    }
}