package com.example.o_bako.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.room.Room
import com.example.o_bako.Database.DBHelper
import com.example.o_bako.Login
import com.example.o_bako.R
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_setting.*
import org.jetbrains.anko.doAsync

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_setting, container, false)

        val edit_Password = view.findViewById<EditText>(R.id.edit_password)
        val edit_Name = view.findViewById<EditText>(R.id.edit_name)
        val edit_Email = view.findViewById<EditText>(R.id.edit_email)
        val edit_alamat = view.findViewById<EditText>(R.id.edit_alamat)
        val edit_Phone = view.findViewById<EditText>(R.id.edit_phonenumber)
        val btn_Submit = view.findViewById<Button>(R.id.btn_edit_submit)
        val btn_delete = view.findViewById<Button>(R.id.btn_delete_acc)
        var db = Room.databaseBuilder(
                context!!,
                DBHelper::class.java,
                "obako.db"
        ).build()

        btn_Submit.setOnClickListener {
            var name_New = edit_Name.text.toString()
            var alamat_New = edit_alamat.text.toString()
            doAsync {
                db.userDao().editData(1,name_New, alamat_New)
            }
        }
        btn_delete.setOnClickListener {
            doAsync {
                db.userDao().deleteAccount(1)
            }
            var intentToLogin = Intent(context,Login::class.java)
            startActivity(intentToLogin)
        }
        return view
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