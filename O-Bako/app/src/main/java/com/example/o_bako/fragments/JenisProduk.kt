package com.example.o_bako.fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.o_bako.DatabaseSQLite.DBHelperSQLite
import com.example.o_bako.DatabaseSQLite.Data
import com.example.o_bako.JenisBarangRecycleAdapter
import com.example.o_bako.R
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [JenisProduk.newInstance] factory method to
 * create an instance of this fragment.
 */
class JenisProduk : Fragment()
//        , ModelVInterface
{
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private lateinit var myJenisBarangRecycleAdapter : JenisBarangRecycleAdapter
    private var Stock : MutableList<Data> = mutableListOf()
    private var mySQLite : DBHelperSQLite? = null
//    var myPresenter = ModelPresenter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mySQLite = DBHelperSQLite(context!!)
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_jenis_produk, container, false)
        val itemList = view.findViewById<RecyclerView>(R.id.itemView)
        val btn_addBarang = view.findViewById<Button>(R.id.addBarang)
//        var strUrl = "https://i.pinimg.com/originals/a1/c6/84/a1c684556890ce23c4811e32c2b882a7.png"
//        myPresenter.initRecycle()

        btn_addBarang.setOnClickListener {
            dialogAddBarang()
            readList()
        }
        myJenisBarangRecycleAdapter = JenisBarangRecycleAdapter(Stock)
        itemList.adapter = myJenisBarangRecycleAdapter
        itemList.layoutManager = LinearLayoutManager(this.activity)
        return view
    }

    private fun dialogAddBarang() {
        mySQLite = DBHelperSQLite(context!!)
        var BuilderDialog = AlertDialog.Builder(context)
        var inflater = layoutInflater.inflate(R.layout.alert_add_barang,null)

        BuilderDialog.setView(inflater)
        BuilderDialog.setPositiveButton("Add"){ dialogInterface: DialogInterface, i: Int ->
            var nama = inflater.findViewById<EditText>(R.id.nama_barang)
            var deskripsi = inflater.findViewById<EditText>(R.id.deskripsi_barang)
            var qty = inflater.findViewById<EditText>(R.id.qty_barang)
            var harga = inflater.findViewById<EditText>(R.id.cost_barang)

            var nama_temp = nama.text.toString()
            var deskripsi_temp = deskripsi.text.toString()
            var qty_temp = qty.text.toString()
            var harga_temp = harga.text.toString()

            doAsync {
                mySQLite?.addData(Data(0,nama_temp,deskripsi_temp,qty_temp,harga_temp))
                uiThread {
                    Toast.makeText(context,"Added!",Toast.LENGTH_SHORT).show()
                }
            }
        }
        BuilderDialog.create().show()
    }

    private fun readList() {
        mySQLite = DBHelperSQLite(context!!)
        var hasil = ""
        doAsync {
            Stock = mySQLite?.getBarang()!!.toMutableList()
            for (data in Stock)
                hasil+= "${data.Nama} ${data.Deskripsi} ${data.Qty} ${data.Harga_Barang}"
            uiThread {
                Log.w("Hasil", hasil)
            }
        }
    }

//  Fungsi untuk melakukan pemprosesan URL untuk mendapatkan gambar
//    private fun processBitMap(url: String): Bitmap? {
//        return try{
//            var url = URL(url)
//            val connection : HttpURLConnection = url.openConnection() as HttpURLConnection
//            connection.doInput = true;
//            connection.connect()
//            val input: InputStream = connection.inputStream
//            val myBitMap = BitmapFactory.decodeStream(input)
//            myBitMap
//        }catch (e: IOException){
//            e.printStackTrace()
//            null
//        }
//    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment JenisProduk.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            JenisProduk().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

//MVP
//    override fun addList(model: MainModel) {
//    }
//
//    override fun initList(model: MainModel) {
//        Stock = model.item
//    }
}