package com.example.o_bako.fragments

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.o_bako.EXTRA_USERNAME
import com.example.o_bako.R
import com.example.o_bako.services.MyAlarm
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_main_home.*
import java.util.*


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

    private var myPendingIntent: PendingIntent? = null
    private var sendIntent: Intent? = null
    private var myAlarmManager: AlarmManager? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main_home, container, false)

        var myAlarmManager = requireContext().getSystemService(Context.ALARM_SERVICE) as AlarmManager

        interfaceData = activity as InterfaceData
        val loginTxt = view.findViewById<TextView>(R.id.login_name)
        val veggies_product = view.findViewById<TextView>(R.id.veggies_opt)
        val food_product = view.findViewById<TextView>(R.id.food_opt)
        val drinks_product = view.findViewById<TextView>(R.id.drink_opt)
        val others_product = view.findViewById<TextView>(R.id.others_opt)
        val icon_notify = view.findViewById<ImageView>(R.id.notify_icon)

        val myPesan = arguments?.getString(EXTRA_USERS)

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

        icon_notify.setOnClickListener{
            var myTimer = Calendar.getInstance()
            myTimer.add(Calendar.SECOND,5)
            sendIntent = Intent(this.activity,MyAlarm::class.java)
            myPendingIntent = PendingIntent.getBroadcast(this.activity,565,sendIntent,0)
            myAlarmManager?.set(AlarmManager.RTC,myTimer.timeInMillis,myPendingIntent)
            Toast.makeText(this.context,"We will inform you if we got Promo!", Toast.LENGTH_LONG).show()
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