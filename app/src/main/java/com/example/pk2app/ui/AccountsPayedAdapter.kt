package com.example.pk2app.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pk2app.Board
import com.example.pk2app.R

class AccountsPayedAdapter(payedBoards: MutableList<Board>) : RecyclerView.Adapter<AccountsPayedAdapter.ViewHolder>() {

    var payedBoards = payedBoards

    private lateinit var mListener : onItemClickLister

    interface onItemClickLister{

        fun onItemClick(id: Int)
    }

    fun setOnItemClickListener(listener: onItemClickLister){
        mListener = listener
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.card_accounts_payed, viewGroup, false)
        return ViewHolder(v, mListener)

    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.itemTable.text = payedBoards[i].getBoard()
        viewHolder.itemUser.text = payedBoards[i].getCustomer()
        viewHolder.itemPrice.text = "$ "+payedBoards[i].getTotalPrice()

    }

    override fun getItemCount(): Int {
        return payedBoards.size
    }

    inner class ViewHolder(itemView: View, listener: onItemClickLister): RecyclerView.ViewHolder(itemView){
        var itemTable: TextView
        var itemUser: TextView
        var itemPrice: TextView

        init{
            itemTable = itemView.findViewById(R.id.itemTable)
            itemUser = itemView.findViewById(R.id.itemUser)
            itemPrice = itemView.findViewById(R.id.itemPrice)

            itemView.setOnClickListener {
                listener.onItemClick(payedBoards[adapterPosition].getId())
            }
        }
    }

}