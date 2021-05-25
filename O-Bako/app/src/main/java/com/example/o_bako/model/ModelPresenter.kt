package com.example.o_bako.model

import android.content.res.Resources
import com.example.o_bako.Data
import com.example.o_bako.R

class ModelPresenter (setView : ModelVInterface) {
    private var view = setView
    private var mainModel = MainModel()

    fun addList(img : Int, nama : String, deskripsi : String, quantity : String, harga_brg : String){
        var temp = mainModel.item
        temp.add(Data(img,nama,deskripsi,quantity,harga_brg))
        view.addList(mainModel)
    }
    fun initRecycle(){
        mainModel.item.add(
                Data(R.drawable.icon_blue,
                "Kentang",
                "Kentang Kuning Manis",
                "1",
                "3000"))
        mainModel.item.add(
                Data(R.drawable.icon_blue,
                "Jagung",
                "Jagung Fresh yang baru dipetik",
                "2",
                "1500"))
        view.initList(mainModel)
    }
}