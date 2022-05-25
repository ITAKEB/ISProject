package com.example.pk2app.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pk2app.Item
import com.example.pk2app.R

class ItemsAdapter ( items: MutableList<Item>) : RecyclerView.Adapter<ItemsAdapter.ViewHolder>(){

    val items = items

    private lateinit var mListener : onItemClickLister

    interface onItemClickLister{

        fun onItemClick(i: Int)
    }

    fun setOnItemClickListener(listener: onItemClickLister){
        mListener = listener
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.card_item, viewGroup, false)
        return ViewHolder(v,mListener)

    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.itemTitle.text = items[i].getName()
        viewHolder.itemDescription.text = items[i].getDescription()

    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View, listener: onItemClickLister): RecyclerView.ViewHolder(itemView){
        var itemTitle: TextView
        var itemDescription: TextView

        init{
            itemTitle = itemView.findViewById(R.id.itemTitle)
            itemDescription = itemView.findViewById(R.id.itemDescription)

            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

}