package com.example.schedule.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.schedule.R


//TODO change data type and name in constructor
class CustomRecyclerAdapter(private val names: List<String>, private val room:List<String>, private val time:List<String>) :
    RecyclerView.Adapter<CustomRecyclerAdapter.MyViewHolder>() {
    private var lastPosition = -1
    private  var contextVar: Context? = null

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val largeTextView: TextView = itemView.findViewById(R.id.textViewLarge)
        val smallTextView: TextView = itemView.findViewById(R.id.textViewSmall)
        val timeTextView: TextView = itemView.findViewById(R.id.textViewTime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        contextVar = parent.context
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.largeTextView.text = names[position]
        holder.smallTextView.text = room[position]
        holder.timeTextView.text = time[position]

    }

    override fun getItemCount(): Int {
        return names.size
    }

}
