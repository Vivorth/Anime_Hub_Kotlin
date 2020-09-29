package com.example.anime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.models.SlideModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_load_recycler.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class load_recycler : AppCompatActivity() {
    lateinit var fstore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_load_recycler)
        fstore = FirebaseFirestore.getInstance()
        val items = ArrayList<item>()
        val items1 = ArrayList<item1>()
        val items2 = ArrayList<item2>()

        var load_recycler = intent.getStringExtra("load_recycler")
        if(load_recycler=="from_recycler"){
            var documentReference = fstore.collection("Anime_Hub").document("Anime_Hub")
            documentReference.get().addOnCompleteListener {
                if(it.isSuccessful){
                    var snapshot = it.getResult()
                    var title = snapshot!!.getString("title")
                    var image = snapshot!!.getString("image")
                    var split_image = image?.split(",")?.toTypedArray()
                    var split_title = title?.split(",")?.toTypedArray()

                    for(i in 0..split_title!!.size-1){
                        items.add(item(split_image!![i], split_title[i]))
                        load_recyclerview.setHasFixedSize(true)
                        load_recyclerview.layoutManager = GridLayoutManager(applicationContext, 3)
                        var adapter: RecyclerView.Adapter<main_list_adapter.itemViewHolder> = main_list_adapter(
                            items
                        )

                        load_recyclerview.adapter = adapter


                    }
                }
            }
        }
        else if(load_recycler=="from_recycler1"){
            var documentReference1 = fstore.collection("Anime_Hub").document("Anime_Hub")
            documentReference1.get().addOnCompleteListener {
                if(it.isSuccessful){
                    var snapshot = it.getResult()
                    var title = snapshot!!.getString("title1")
                    var image = snapshot!!.getString("image1")
                    var split_image = image?.split(",")?.toTypedArray()
                    var split_title = title?.split(",")?.toTypedArray()

                    for(i in 0..split_title!!.size-1){
                        items1.add(item1(split_image!![i], split_title[i]))
                        load_recyclerview.setHasFixedSize(true)
                        load_recyclerview.layoutManager = GridLayoutManager(applicationContext, 3)
                        var adapter: RecyclerView.Adapter<main_list_adapter1.itemViewHolder> = main_list_adapter1(
                            items1
                        )

                        load_recyclerview.adapter = adapter


                    }
                }
            }
        }
        else if(load_recycler=="from_recycler2"){
            var documentReference2 = fstore.collection("Anime_Hub").document("Anime_Hub")
            documentReference2.get().addOnCompleteListener {
                if(it.isSuccessful){
                    var snapshot = it.getResult()
                    var title = snapshot!!.getString("title2")
                    var image = snapshot!!.getString("image2")
                    var split_image = image?.split(",")?.toTypedArray()
                    var split_title = title?.split(",")?.toTypedArray()

                    for(i in 0..split_title!!.size-1){
                        items2.add(item2(split_image!![i], split_title[i]))
                        load_recyclerview.setHasFixedSize(true)
                        load_recyclerview.layoutManager = GridLayoutManager(applicationContext, 3)
                        var adapter: RecyclerView.Adapter<main_list_adapter2.itemViewHolder> = main_list_adapter2(
                            items2
                        )

                        load_recyclerview.adapter = adapter

                    }
                }
            }
        }






    }
}