package com.example.pk2app.ui

import Data.DataDbHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pk2app.Board
import com.example.pk2app.R
import com.google.android.material.button.MaterialButton

class AccountsPayedAdapter(payedBoards: MutableList<Board>) : RecyclerView.Adapter<AccountsPayedAdapter.ViewHolder>() {

    var payedBoards = payedBoards

    private lateinit var mListener : onItemClickLister
    private lateinit var mBtListener : onBtClickLister

    private lateinit var db: DataDbHelper

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
            LayoutInflater.from(viewGroup.context).inflate(R.layout.card_accounts_payed, viewGroup, false)
        return ViewHolder(v, mListener, mBtListener)

    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.itemTable.text = payedBoards[i].getBoard()
        viewHolder.itemUser.text = payedBoards[i].getCustomer()
        viewHolder.itemPrice.text = "$ "+payedBoards[i].getTotalPrice()

    }

    override fun getItemCount(): Int {
        return payedBoards.size
    }

    inner class ViewHolder(itemView: View, listener: onItemClickLister, btlistener: onBtClickLister): RecyclerView.ViewHolder(itemView){
        var itemTable: TextView
        var itemUser: TextView
        var itemPrice: TextView
        var deleteBoard: MaterialButton
        init{
            itemTable = itemView.findViewById(R.id.itemTable)
            itemUser = itemView.findViewById(R.id.itemUser)
            itemPrice = itemView.findViewById(R.id.itemPrice)
            deleteBoard = itemView.findViewById(R.id.btDelete)

            deleteBoard.setOnClickListener {
                btlistener.onBtClick(payedBoards[adapterPosition].getId())
            }

            itemView.setOnClickListener {
                listener.onItemClick(payedBoards[adapterPosition].getId())
            }

        }
    }

}