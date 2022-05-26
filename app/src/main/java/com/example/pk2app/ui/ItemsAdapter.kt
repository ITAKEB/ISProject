package com.example.pk2app.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pk2app.Item
import com.example.pk2app.R
import com.google.android.material.button.MaterialButton

class ItemsAdapter ( items: MutableList<Item>) : RecyclerView.Adapter<ItemsAdapter.ViewHolder>(){

    val items = items

    private lateinit var mListener : onItemClickLister
    private lateinit var mBtListener : onBtClickLister

    interface onItemClickLister{

        fun onItemClick(id: Int)
    }

    interface onBtClickLister{

        fun onBtClick(id: Int)

    }

    fun setOnItemClickListener(listener: onItemClickLister){
        mListener = listener
    }

    fun setOnBtDeleteClickListener(btlistener: onBtClickLister){
        mBtListener = btlistener
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.card_item, viewGroup, false)
        return ViewHolder(v,mListener,mBtListener)

    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.itemTitle.text = items[i].getName()
        viewHolder.itemDescription.text = items[i].getDescription()

    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View, listener: onItemClickLister, btlistener: onBtClickLister): RecyclerView.ViewHolder(itemView){
        var itemTitle: TextView
        var itemDescription: TextView
        var deleteItem: MaterialButton

        init{
            itemTitle = itemView.findViewById(R.id.itemTitle)
            itemDescription = itemView.findViewById(R.id.itemDescription)
            deleteItem = itemView.findViewById(R.id.btDeleteItem)

            deleteItem.setOnClickListener {
                btlistener.onBtClick(items[adapterPosition].getId())
            }

            itemView.setOnClickListener {
                listener.onItemClick(items[adapterPosition].getId())
            }
        }
    }

}