package com.example.o_bako.Fragments

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.o_bako.InviteFriend
import com.example.o_bako.MainActivity
import com.example.o_bako.R
import com.example.o_bako.Services.*
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
    
    val notification_channel1 = 1
    val ch_id = "Notify"
    val desc_channel = "Notifications"

    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder: Notification.Builder

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main_home, container, false)
        notificationManager = requireContext()
                .getSystemService(Context.NOTIFICATION_SERVICE)
                as NotificationManager

        val intent = Intent(this.activity, MainActivity::class.java)
        val pendingIntent = TaskStackBuilder.create(context)
                .addNextIntentWithParentStack(intent)
                .getPendingIntent(514, PendingIntent.FLAG_UPDATE_CURRENT)

        val veggies_product = view.findViewById<TextView>(R.id.veggies_opt)
        val icon_notify = view.findViewById<ImageView>(R.id.notify_icon)
        val icon_add = view.findViewById<ImageView>(R.id.invite_icon)

        veggies_product.setOnClickListener {
            val fragmentManager: FragmentManager = activity!!.supportFragmentManager
            val fragment_jenisProduk = JenisProduk()
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.myContainer, fragment_jenisProduk)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

        icon_add.setOnClickListener {
            var intentToAdd = Intent(this.activity, InviteFriend::class.java)
            startActivity(intentToAdd)
        }

        icon_notify.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationChannel = NotificationChannel(ch_id,
                        desc_channel,
                        NotificationManager.IMPORTANCE_LOW)
                notificationChannel.enableLights(true)
                notificationChannel.setShowBadge(true)
                notificationManager.createNotificationChannel(notificationChannel)

                builder = Notification.Builder(activity, ch_id)
                        .setContentTitle("Thank you for Enabling Notification")
                        .setContentText("See ya on Next Notification")
                        .setSmallIcon(R.drawable.icon_blue)
                        .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable.icon_blue))
                        .setShowWhen(true)
                        .setAutoCancel(true)
                        .setContentIntent(pendingIntent)
            }
            else {
                builder = Notification.Builder(activity)
                        .setContentTitle("Thank you for Enabling Notification")
                        .setContentText("See ya on Next Notification")
                        .setSmallIcon(R.drawable.icon_blue)
                        .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable.icon_blue))
                        .setContentIntent(pendingIntent)
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

        fun newInstance(param1: String) =
            MainHome().apply {
                arguments = Bundle().apply {
                    putString(EXTRA_USERS, param1)
                }
            }
    }
}