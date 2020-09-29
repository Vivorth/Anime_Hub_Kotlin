package com.example.anime

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.anime.R
import com.example.anime.load
import com.example.anime.sub_item
import kotlinx.android.synthetic.main.activity_load.*
import kotlinx.android.synthetic.main.sub_list_card.view.*

class sub_list_adapter(sub_items: ArrayList<sub_item>, a: load) : RecyclerView.Adapter<sub_list_adapter.itemViewHolder>() { // "a" is to use object of load activity
lateinit var context:Context
    var msub_item: java.util.ArrayList<sub_item>?
    var a1:load
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): sub_list_adapter.itemViewHolder {
        context = parent.context
        var v = LayoutInflater.from(context).inflate(R.layout.sub_list_card, parent, false)

        var i:itemViewHolder = itemViewHolder(v)
        return i
    }
init {
    msub_item = sub_items
    a1 = a

}
class itemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
}
    override fun onBindViewHolder(holder: itemViewHolder, position: Int) {
        var current_item_position: sub_item = msub_item!!.get(position)
        var id1 = current_item_position.getmSub_image()
        context?.let { Glide.with(it).load(id1).centerCrop().placeholder(
            R.drawable.loading_placeholder
        ).into(holder.itemView.sub_image) }
        var temp = position+1
        holder.itemView.length.setText(current_item_position.getmLength().toString()+" episode $temp")
        holder.itemView.length.bringToFront()

        holder.itemView.setOnClickListener(
            View.OnClickListener {
                a1.id = current_item_position.getmYoutubeID().toString()

                a1.button.performClick() // to make button auto click so then it will refresh new ID to youtube player
            }
        )

    }

    override fun getItemCount(): Int {
        return msub_item!!.size
    }




}

