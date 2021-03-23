package com.example.o_bako.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.o_bako.EXTRA_USERDOANK
import com.example.o_bako.EXTRA_USERNAME
import com.example.o_bako.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_main_home.*
import org.w3c.dom.Text

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MainHome.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainHome : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }
    }

    lateinit var interfaceData: InterfaceData
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main_home, container, false)

        interfaceData = activity as InterfaceData

        val loginTxt = view.findViewById<TextView>(R.id.login_name)
        val veggies_product = view.findViewById<TextView>(R.id.veggies_opt)
        val food_product = view.findViewById<TextView>(R.id.food_opt)
        val drinks_product = view.findViewById<TextView>(R.id.drink_opt)
        val others_product = view.findViewById<TextView>(R.id.others_opt)
//      mengambil data dari argument melalui EXTRA
        val myPesan = arguments?.getString(EXTRA_USERS)
//        mengisi text view TextView dengan variable myPesan
        loginTxt.text = "Login as, $myPesan"

        veggies_product.setOnClickListener {
            interfaceData.Kirim(veggies_product.text.toString())
        }
        food_product.setOnClickListener {
            interfaceData.Kirim(food_product.text.toString())
        }
        drinks_product.setOnClickListener {
            interfaceData.Kirim(drinks_product.text.toString())
        }
        others_product.setOnClickListener {
            interfaceData.Kirim(others_product.text.toString())
        }

        return view
    }

    companion object {
        const val EXTRA_USERS = "12345"
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MainHome.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic

//        menerima data dari MainActivity melalui fungsi newInstance
//        kemudian dimasukan kedalam sebuah Extra
//        mengisikan isi argument berdasarkan data yang dimasukan pada key kedalam fungsi Bundle
//        *Fungsi newInstance dapat ditambah parameternya sesuai dengan kebutuhan
        fun newInstance(param1: String) =
            MainHome().apply {
                arguments = Bundle().apply {
                    putString(EXTRA_USERS, param1)
                }
            }
    }
}