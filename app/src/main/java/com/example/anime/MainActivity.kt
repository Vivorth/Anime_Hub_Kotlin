package com.example.anime

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.models.SlideModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    lateinit var fstore:FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fstore = FirebaseFirestore.getInstance()
        var intent = Intent(applicationContext,load_recycler::class.java)

        popular_more.setOnClickListener {
            intent.putExtra("load_recycler","from_recycler")
            startActivity(intent)
        }
        series_more.setOnClickListener {
            intent.putExtra("load_recycler","from_recycler1")

            startActivity(intent)

        }
        full_more.setOnClickListener {
            intent.putExtra("load_recycler","from_recycler2")

            startActivity(intent)

        }



        val items = ArrayList<item>()
        val items1 = ArrayList<item1>()
        val items2 = ArrayList<item2>()
        val imageList = ArrayList<SlideModel>() // Create image list


        var documentReference0 = fstore.collection("Anime_Hub").document("Anime_Hub")
        documentReference0.get().addOnCompleteListener {
            if(it.isSuccessful){
                var snapshot = it.getResult()
                var image = snapshot!!.getString("image_slider")
                var split_image = image?.split(",")?.toTypedArray()
                for(i in 0..split_image!!.size-1){
                    imageList.add(SlideModel(split_image[i]))
                }
                progressbar.setVisibility(View.GONE)
                popular_linear.visibility = View.VISIBLE
                series_linear.visibility = View.VISIBLE
                full_linear.visibility = View.VISIBLE
            }
            image_slider.setImageList(imageList)
        }


        var documentReference = fstore.collection("Anime_Hub").document("Anime_Hub")
        documentReference.get().addOnCompleteListener {
            if(it.isSuccessful){
                var snapshot = it.getResult()
                var title = snapshot!!.getString("title")
                var image = snapshot!!.getString("image")
                var split_image = image?.split(",")?.toTypedArray()
                var split_title = title?.split(",")?.toTypedArray()
                var linearLayoutManager:LinearLayoutManager =  LinearLayoutManager(applicationContext,LinearLayoutManager.HORIZONTAL,
                false)
                for(i in 0..split_title!!.size-1){
                    items.add(item(split_image!![i], split_title[i]))
                    main_recycler.setHasFixedSize(true)
                    main_recycler.layoutManager = linearLayoutManager
                    main_recycler.itemAnimator = DefaultItemAnimator()
                    var adapter: RecyclerView.Adapter<main_list_adapter.itemViewHolder> = main_list_adapter(
                        items
                    )

                    main_recycler.adapter = adapter


                }
            }
        }
        var documentReference1 = fstore.collection("Anime_Hub").document("Anime_Hub")
        documentReference1.get().addOnCompleteListener {
            if(it.isSuccessful){
                var snapshot = it.getResult()
                var title = snapshot!!.getString("title1")
                var image = snapshot!!.getString("image1")
                var split_image = image?.split(",")?.toTypedArray()
                var split_title = title?.split(",")?.toTypedArray()
                var linearLayoutManager:LinearLayoutManager =  LinearLayoutManager(applicationContext,LinearLayoutManager.HORIZONTAL,
                    false)
                for(i in 0..split_title!!.size-1){
                    items1.add(item1(split_image!![i], split_title[i]))
                    main_recycler1.setHasFixedSize(true)
                    main_recycler1.layoutManager = linearLayoutManager
                    main_recycler1.itemAnimator = DefaultItemAnimator()
                    var adapter: RecyclerView.Adapter<main_list_adapter1.itemViewHolder> = main_list_adapter1(
                        items1
                    )

                    main_recycler1.adapter = adapter


                }
            }
        }
        var documentReference2 = fstore.collection("Anime_Hub").document("Anime_Hub")
        documentReference2.get().addOnCompleteListener {
            if(it.isSuccessful){
                var snapshot = it.getResult()
                var title = snapshot!!.getString("title2")
                var image = snapshot!!.getString("image2")
                var split_image = image?.split(",")?.toTypedArray()
                var split_title = title?.split(",")?.toTypedArray()
                var linearLayoutManager:LinearLayoutManager =  LinearLayoutManager(applicationContext,LinearLayoutManager.HORIZONTAL,
                    false)
                for(i in 0..split_title!!.size-1){
                    items2.add(item2(split_image!![i], split_title[i]))
                    main_recycler2.setHasFixedSize(true)
                    main_recycler2.layoutManager = linearLayoutManager
                    main_recycler2.itemAnimator = DefaultItemAnimator()
                    var adapter: RecyclerView.Adapter<main_list_adapter2.itemViewHolder> = main_list_adapter2(
                        items2
                    )

                    main_recycler2.adapter = adapter


                }
            }
        }
    }

    /**
     * Called when the activity has detected the user's press of the back
     * key. The [OnBackPressedDispatcher][.getOnBackPressedDispatcher] will be given a
     * chance to handle the back button before the default behavior of
     * [android.app.Activity.onBackPressed] is invoked.
     *
     * @see .getOnBackPressedDispatcher
     */


}