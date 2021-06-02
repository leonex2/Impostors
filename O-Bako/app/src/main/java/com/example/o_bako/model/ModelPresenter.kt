package com.example.o_bako.model

import com.example.o_bako.DatabaseSQLite.Data
import com.example.o_bako.R

class ModelPresenter (setView : ModelVInterface) {
    private var view = setView
    private var mainModel = MainModel()

    fun addList(nama : String, deskripsi : String, quantity : String, harga_brg : String){
        var temp = mainModel.item
        temp.add(Data(nama,deskripsi,quantity,harga_brg))
        view.addList(mainModel)
    }
    fun initRecycle(){
        mainModel.item.add(
                Data("Kentang",
                "Kentang Kuning Manis",
                "1",
                "3000"))
        mainModel.item.add(
                Data("Jagung",
                "Jagung Fresh yang baru dipetik",
                "2",
                "1500"))
        view.initList(mainModel)
    }
}