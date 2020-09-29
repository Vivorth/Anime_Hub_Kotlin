package com.example.anime

import android.content.SharedPreferences
import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.ads.Ad
import com.facebook.ads.AdError
import com.facebook.ads.AudienceNetworkAds
import com.facebook.ads.InterstitialAdListener

import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_load.*
import java.lang.Exception


public class load() : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {
    lateinit var fstore: FirebaseFirestore
    lateinit var interstitialAd: com.facebook.ads.InterstitialAd

    lateinit var name:String
    public var id:String = "6wQ8-Ok4wIQ"
    lateinit var split_youtubeID:Array<String>
    lateinit var split_image:Array<String>
    var count = 0
    var flag = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_load)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT
        AudienceNetworkAds.initialize(this);

        var sub_items = ArrayList<sub_item>()
        var extra: String = intent.getStringExtra("position")
        var split = extra.split("-")
        var extra1 = intent.getIntExtra("position1",0)
        var extra2 = intent.getStringExtra("position2")
        name = split[0]
        var length = name+"_length"
        fstore = FirebaseFirestore.getInstance()
        var documentReference: DocumentReference = fstore.collection("Anime_Hub").document("Anime_Hub")
        documentReference.get().addOnCompleteListener {
            if(it.isSuccessful){
                var snapshot = it.getResult()
                var youtubeID = snapshot!!.getString(name)
                if(extra2=="recycler"){
                    var image = snapshot!!.getString("image")
                    split_image = image!!.split(",").toTypedArray()
                    split_youtubeID = youtubeID!!.split(",").toTypedArray()


                    for(i in 0..split_youtubeID.size-1){
                        sub_items.add(sub_item(split_youtubeID[i],name,split_image[extra1]))
                        sub_recycler.setHasFixedSize(true)

                        sub_recycler.layoutManager = GridLayoutManager(applicationContext, 1)
                        var adapter: RecyclerView.Adapter<sub_list_adapter.itemViewHolder> = sub_list_adapter(
                            sub_items, a = this
                        )

                        sub_recycler.adapter = adapter
                    }
                }
                else if (extra2=="recycler1"){
                    var image = snapshot!!.getString("image1")
                    split_image = image!!.split(",").toTypedArray()
                    split_youtubeID = youtubeID!!.split(",").toTypedArray()


                    for(i in 0..split_youtubeID.size-1){
                        sub_items.add(sub_item(split_youtubeID[i],name,split_image[extra1]))
                        sub_recycler.setHasFixedSize(true)

                        sub_recycler.layoutManager = GridLayoutManager(applicationContext, 1)
                        var adapter: RecyclerView.Adapter<sub_list_adapter.itemViewHolder> = sub_list_adapter(
                            sub_items, a = this
                        )

                        sub_recycler.adapter = adapter
                    }
                }
                else if(extra2=="recycler2"){
                    var image = snapshot!!.getString("image2")
                    split_image = image!!.split(",").toTypedArray()
                    split_youtubeID = youtubeID!!.split(",").toTypedArray()


                    for(i in 0..split_youtubeID.size-1){
                        sub_items.add(sub_item(split_youtubeID[i],name,split_image[extra1]))
                        sub_recycler.setHasFixedSize(true)

                        sub_recycler.layoutManager = GridLayoutManager(applicationContext, 1)
                        var adapter: RecyclerView.Adapter<sub_list_adapter.itemViewHolder> = sub_list_adapter(
                            sub_items, a = this
                        )

                        sub_recycler.adapter = adapter
                    }
                }



            }
        }
        player.initialize("AIzaSyDPuriEBRGHIBHduq4CBLHWbgwQLffe3TY", this)



    }
    override fun onInitializationSuccess(
        p0: YouTubePlayer.Provider?,
        p1: YouTubePlayer?,
        p2: Boolean
    ) {
        if(!p2){

            button.setOnClickListener { // need to use 1 button so that it will refresh id when button is click in recyclerview
                p1?.loadVideo(id)

            }
            p1?.loadVideo(id)


        }

    }

    override fun onInitializationFailure(
        p0: YouTubePlayer.Provider?,
        p1: YouTubeInitializationResult?
    ) {
    }
    /**
     * Dispatch onPause() to fragments.
     */
    override fun onStart() {
        super.onStart()
        interstitialAd =
            com.facebook.ads.InterstitialAd(applicationContext, "675599433080545_675599996413822")

        interstitialAd.setAdListener(object : InterstitialAdListener {
            override fun onInterstitialDisplayed(p0: Ad?) {
            }

            override fun onInterstitialDismissed(p0: Ad?) {
            }

            override fun onAdLoaded(p0: Ad?) {
                if(interstitialAd.isAdLoaded && interstitialAd!=null){
                    interstitialAd.show();

                }


            }

            override fun onAdClicked(p0: Ad?) {
            }

            override fun onLoggingImpression(p0: Ad?) {
            }

            override fun onError(p0: Ad?, p1: AdError?) {
                Log.d("TAG","ERROR11111")
            }

        })
        interstitialAd.loadAd()

    }

    override fun onDestroy() {
        if(interstitialAd!=null){
            interstitialAd.destroy()
        }
        super.onDestroy()
    }

}