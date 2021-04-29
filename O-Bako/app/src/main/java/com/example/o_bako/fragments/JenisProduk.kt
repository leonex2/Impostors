package com.example.o_bako.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.o_bako.Data
import com.example.o_bako.JenisBarangRecycleAdapter
import com.example.o_bako.R
import com.example.o_bako.model.MainModel
import com.example.o_bako.model.ModelPresenter
import com.example.o_bako.model.ModelVInterface

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [JenisProduk.newInstance] factory method to
 * create an instance of this fragment.
 */
class JenisProduk : Fragment(), ModelVInterface {
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

    var nama_jenis_barang : String? = ""
    private lateinit var myJenisBarangRecycleAdapter : JenisBarangRecycleAdapter
    private var Stock : MutableList<Data> = mutableListOf()
    var myPresenter = ModelPresenter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_jenis_produk, container, false)
//        var strUrl = "https://i.pinimg.com/originals/a1/c6/84/a1c684556890ce23c4811e32c2b882a7.png"
        val namaTxt = view.findViewById<TextView>(R.id.nama_product)
        val itemList = view.findViewById<RecyclerView>(R.id.itemView)

        nama_jenis_barang = arguments?.getString("Pesan")
        namaTxt.text = nama_jenis_barang

        myPresenter.initRecycle()

        myJenisBarangRecycleAdapter = JenisBarangRecycleAdapter(Stock)
        itemList.adapter = myJenisBarangRecycleAdapter
        itemList.layoutManager = LinearLayoutManager(this.activity)


        return view
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

    override fun addList(model: MainModel) {
        TODO("Not yet implemented")
    }

    override fun initList(model: MainModel) {
        Stock = model.item
    }
}