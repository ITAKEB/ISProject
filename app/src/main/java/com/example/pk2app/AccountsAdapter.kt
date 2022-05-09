package com.example.pk2app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AccountsAdapter: RecyclerView.Adapter<AccountsAdapter.ViewHolder>() {

    val tables = arrayOf("Mesa 1", "Mesa 2", "Mesa 3", "Mesa 4")

    val users = arrayOf("Tomas", "Viviana", "Alfonso", "Sergio")

    val prices = arrayOf("15000", "2000", "30000", "80000")

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.card_accounts, viewGroup, false)
        return ViewHolder(v)

    }

    override fun onBindViewHolder(viewHolder: AccountsAdapter.ViewHolder, i: Int) {
        viewHolder.itemTable.text = tables[i]
        viewHolder.itemUser.text = users[i]
        viewHolder.itemPrice.text = "$ "+prices[i]

    }

    override fun getItemCount(): Int {
        return tables.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var itemTable: TextView
        var itemUser: TextView
        var itemPrice: TextView

        init{
            itemTable = itemView.findViewById(R.id.itemTable)
            itemUser = itemView.findViewById(R.id.itemUser)
            itemPrice = itemView.findViewById(R.id.itemPrice)
        }
    }
}