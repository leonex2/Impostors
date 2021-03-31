package com.example.o_bako.fragments

<<<<<<< HEAD
import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
=======
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
>>>>>>> origin/main
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
<<<<<<< HEAD
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import com.example.o_bako.Login
import com.example.o_bako.MainActivity
=======

>>>>>>> origin/main
import com.example.o_bako.R
import com.example.o_bako.SignUp
import com.example.o_bako.others.NotificationReceiver
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_main_home.*


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

    val notification_channel1 = 1
    val ch_id = "com.example.o_bako.fragments"
    val desc_channel = "Promo"
    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder: Notification.Builder

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main_home, container, false)

        notificationManager = requireContext().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        interfaceData = activity as InterfaceData


        //val intent_2 = Intent(this.activity, Login::class.java).apply {
        //    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        //}
        //val pendingIntent_2: PendingIntent = PendingIntent.getActivity(this.activity, 0, intent_2,0)

        val intent_2 = Intent(this.activity, NotificationReceiver::class.java)
        val pendingIntent_2: PendingIntent = PendingIntent.getBroadcast(this.activity,0,intent_2,0)

        val loginTxt = view.findViewById<TextView>(R.id.login_name)
        val veggies_product = view.findViewById<TextView>(R.id.veggies_opt)
        val food_product = view.findViewById<TextView>(R.id.food_opt)
        val drinks_product = view.findViewById<TextView>(R.id.drink_opt)
        val others_product = view.findViewById<TextView>(R.id.others_opt)
        val icon_notify = view.findViewById<ImageView>(R.id.notify_icon)

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

        icon_notify.setOnClickListener{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {


                notificationChannel = NotificationChannel(ch_id, desc_channel, NotificationManager.IMPORTANCE_HIGH)
                notificationChannel.enableLights(true)
                notificationChannel.setShowBadge(true)
                notificationManager.createNotificationChannel(notificationChannel)

                builder = Notification.Builder(activity, ch_id)
<<<<<<< HEAD
                        .setContentTitle("Diskon Akhir Bulan")
                        .setContentText("Login sekarang dan nikmati diskon nya!")
=======
                        .setContentTitle("Promo Akhir Bulan")
                        .setContentText("Promo ini hanya berlaku selama 3 hari !")
>>>>>>> origin/main
                        .setSmallIcon(R.drawable.icons8_notifications)
                        .addAction(
                        R.drawable.icon8_cart,"VEGIE",pendingIntent_2);

            }
            else {
                builder = Notification.Builder(activity)
                        .setContentTitle("Monthly Promo")
                        .setContentText("Promo ini hanya berlaku selama 3 hari !")
                        .setSmallIcon(R.drawable.icons8_notifications)
                        .addAction(
                        R.drawable.icon8_cart,"SNOOZE",pendingIntent_2);

            }
            notificationManager.notify(notification_channel1, builder.build())
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