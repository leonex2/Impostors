package com.example.o_bako.fragments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.example.o_bako.R
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_setting.*
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Setting.newInstance] factory method to
 * create an instance of this fragment.
 */
class Setting : Fragment() {
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

    var myFile : File? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_setting, container, false)
        val new_alamat = view.findViewById<TextView>(R.id.my_new_alamat)
        val edit_new = view.findViewById<EditText>(R.id.edit_new_alamat)
        val btn_submit = view.findViewById<Button>(R.id.btn_edit_submit)
        val btn_check = view.findViewById<Button>(R.id.btn_check)

        btn_check.setOnClickListener {
            readCache()
        }
        btn_submit.setOnClickListener {
            writeCache()
            edit_new.setText("")
        }
        return view
    }
    private fun readCache() {
        if(myFile!=null) {
            val stream = myFile?.inputStream()
            val bytes = ByteArray(16)
            stream?.read(bytes)
            stream?.close()
            my_new_alamat.setText(bytes?.toString(Charsets.UTF_8))
        }
    }
    private fun writeCache() {
        myFile = File.createTempFile("change",null)
        myFile?.writeText(edit_new_alamat.text.toString())
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Setting.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Setting().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}