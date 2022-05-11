package com.example.pk2app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AccountsAdapter: RecyclerView.Adapter<AccountsAdapter.ViewHolder>() {

    val tables = arrayOf("Mesa 1", "Mesa 2", "Mesa 3", "Mesa 4")

    val users = arrayOf("Tomas", "Viviana", "Alfonso", "Sergio")

    val prices = arrayOf("15000", "2000", "30000", "80000")

    private lateinit var mListener : onItemClickLister

    interface onItemClickLister{

        fun onItemClick(i: Int)
    }

    fun setOnItemClickListener(listener: onItemClickLister){
        mListener = listener
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.card_accounts, viewGroup, false)
        return ViewHolder(v, mListener)

    }

    override fun onBindViewHolder(viewHolder: AccountsAdapter.ViewHolder, i: Int) {
        viewHolder.itemTable.text = tables[i]
        viewHolder.itemUser.text = users[i]
        viewHolder.itemPrice.text = "$ "+prices[i]

    }

    override fun getItemCount(): Int {
        return tables.size
    }

    inner class ViewHolder(itemView: View, listener: onItemClickLister): RecyclerView.ViewHolder(itemView){
        var itemTable : TextView = itemView.findViewById(R.id.itemTable)
        var itemUser : TextView = itemView.findViewById(R.id.itemUser)
        var itemPrice : TextView = itemView.findViewById(R.id.itemPrice)

        init{

            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }
}