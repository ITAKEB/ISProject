package com.example.pk2app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemsAccountAdapter: RecyclerView.Adapter<ItemsAccountAdapter.ViewHolder>() {

    val items = arrayOf("Cerveza Aguila", "Shot 1", "Aguardiente", "Ronsito")

    val counts = arrayOf("0", "5", "10", "11")

    val prices = arrayOf("0", "5000", "10000", "11000")


    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.card_item_account, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ItemsAccountAdapter.ViewHolder, i: Int) {
        viewHolder.itemAccountTitle.text = items[i]
        viewHolder.itemAccountCount.text = counts[i]
        viewHolder.itemAccountPrice.text = "$"+prices[i]

    }

    override fun getItemCount(): Int {
        return items.size
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