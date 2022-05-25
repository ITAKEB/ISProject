package com.example.pk2app.ui

import Data.Tables
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pk2app.ItemBoard
import com.example.pk2app.R

class ItemsAccountAdapter(dataItemBoard: MutableList<ItemBoard>): RecyclerView.Adapter<ItemsAccountAdapter.ViewHolder>() {

    val itemsBoards =  dataItemBoard


    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.card_item_account, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.itemAccountTitle.text = itemsBoards[i].getItemTitle()
        viewHolder.itemAccountCount.text = itemsBoards[i].getQuantity().toString()
        viewHolder.itemAccountPrice.text = "$"+itemsBoards[i].getItemPrice()

    }

    override fun getItemCount(): Int {
        return itemsBoards.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var itemAccountTitle: TextView
        var itemAccountCount: TextView
        var itemAccountPrice: TextView

        init{
            itemAccountTitle = itemView.findViewById(R.id.itemAccountTitle)
            itemAccountCount = itemView.findViewById(R.id.itemAccountCount)
            itemAccountPrice = itemView.findViewById(R.id.itemAccountPrice)

        }
    }
}