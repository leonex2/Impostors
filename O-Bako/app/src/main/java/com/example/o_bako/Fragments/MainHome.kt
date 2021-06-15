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
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.o_bako.Activities.InviteFriend
import com.example.o_bako.Activities.MainActivity
import com.example.o_bako.Others.AdsSharedPreference
import com.example.o_bako.R
import com.example.o_bako.Services.*
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.OnUserEarnedRewardListener
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
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
    private var myInterstitialAd : InterstitialAd?=null
    private var myRewardVid : RewardedAd ?= null

    private val filename = "AdsRemoverTime"
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main_home, container, false)
        MobileAds.initialize(context)
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
        val icon_AdsReward = view.findViewById<ImageView>(R.id.video_icon)

        icon_AdsReward.visibility = View.GONE

        MobileAds.initialize(activity){}
        RewardedAd.load(context, "ca-app-pub-3940256099942544/5224354917",
                AdRequest.Builder().build(),
                object : RewardedAdLoadCallback(){
                    override fun onAdFailedToLoad(p0: LoadAdError) {
                        Toast.makeText(context,"No Internect Connection", Toast.LENGTH_SHORT).show()
                        super.onAdFailedToLoad(p0)
                        myRewardVid = null
                    }
                    override fun onAdLoaded(p0: RewardedAd) {
                        super.onAdLoaded(p0)
                        myRewardVid = p0
                        icon_AdsReward.visibility = View.VISIBLE
                    }
                })

        InterstitialAd.load(context,"ca-app-pub-3940256099942544/1033173712",
            AdRequest.Builder().build(), object : InterstitialAdLoadCallback(){
                override fun onAdFailedToLoad(p0: LoadAdError) {
                    Toast.makeText(context, "No Internect Connection", Toast.LENGTH_SHORT).show()
                    myInterstitialAd = null
                }

                override fun onAdLoaded(p0: InterstitialAd) {
                    super.onAdLoaded(p0)
                    myInterstitialAd = p0
                }
            })

        icon_AdsReward.setOnClickListener {
            var myAdsRemover = AdsSharedPreference(context!!, filename)
            var watchLeft = myAdsRemover.watchTime
            myAdsRemover.watchTime = watchLeft - 1
            if(myRewardVid!=null && watchLeft > 0){
                myRewardVid?.show(this.activity, OnUserEarnedRewardListener() {
                    Toast.makeText(this.activity,"Watch ${myAdsRemover.watchTime} more to remove ads",
                            Toast.LENGTH_SHORT).show()
                })
            }
            else{
                icon_AdsReward.visibility = View.GONE
                Toast.makeText(activity,"Ads Removed !",Toast.LENGTH_SHORT).show()
            }
        }

        veggies_product.setOnClickListener {
            showInterstitial()
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

    private fun showInterstitial() {
        var myAdsRemover = AdsSharedPreference(context!!, filename)
        var timeLeft = myAdsRemover.watchTime
        if(myInterstitialAd!=null && timeLeft > 0){
            myInterstitialAd?.show(this.activity)
        }
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