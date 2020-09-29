package com.example.anime

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.main_list_card.view.*
import java.util.ArrayList

class main_list_adapter2(items: ArrayList<item2>) : RecyclerView.Adapter<main_list_adapter2.itemViewHolder>() {
    lateinit var context:Context
    var mitems: java.util.ArrayList<item2>?
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): main_list_adapter2.itemViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.main_list_card, parent, false)
        context = parent.context
        var i:itemViewHolder = itemViewHolder(v)
        return i
    }
    init {
        mitems = items
    }
    class itemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    }
    override fun onBindViewHolder(holder: main_list_adapter2.itemViewHolder, position: Int) {
        var current_item_position:item2 = mitems!!.get(position)
        holder.itemView.title.setText(current_item_position.title)
        context?.let { Glide.with(it).load(current_item_position.image_resource).centerCrop().placeholder(R.drawable.loading_placeholder).into(holder.itemView.image) }
        holder.itemView.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when (event?.action) {
                    MotionEvent.ACTION_DOWN -> holder.itemView.linear.setBackgroundColor(Color.parseColor("#20000000"))
                    MotionEvent.ACTION_UP -> holder.itemView.linear.setBackgroundColor(Color.WHITE)

                    MotionEvent.ACTION_CANCEL -> holder.itemView.linear.setBackgroundColor(Color.WHITE)

                }

                return v?.onTouchEvent(event) ?: true
            }
        })

        holder.itemView.setOnClickListener(View.OnClickListener {
            val i = Intent(context,load::class.java)
            i.putExtra("position",current_item_position.title)
            i.putExtra("position1",position)
            i.putExtra("position2","recycler2")
            context.startActivity(i)

        })

    }

    override fun getItemCount(): Int {
        return mitems!!.size
    }

}