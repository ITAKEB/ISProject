package com.example.pk2app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemsAdapter : RecyclerView.Adapter<ItemsAdapter.ViewHolder>(){

    val items = arrayOf("Cerveza Aguila", "Shot 1", "Aguardiente", "Ronsito")

    val description = arrayOf("110ml", "Misterioso", "52ml", "10ml")

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.card_item, viewGroup, false)
        return ViewHolder(v)

    }

    override fun onBindViewHolder(viewHolder: ItemsAdapter.ViewHolder, i: Int) {
        viewHolder.itemTitle.text = items[i]
        viewHolder.itemDescription.text = description[i]

    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var itemTitle: TextView
        var itemDescription: TextView

        init{
            itemTitle = itemView.findViewById(R.id.itemTitle)
            itemDescription = itemView.findViewById(R.id.itemDescription)
        }
    }

}