package com.example.pk2app.ui

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.pk2app.Board
import com.example.pk2app.Item
import com.example.pk2app.R
import java.util.stream.Collector
import java.util.stream.Collectors

class AccountsAdapter(boardsList: MutableList<Board>) :
    RecyclerView.Adapter<AccountsAdapter.ViewHolder>() {

    val boards = boardsList
    var boardsOriginal:MutableList<Board> = arrayListOf()

    init {
        boardsOriginal.addAll(boardsList)
    }

    private lateinit var mListener: onItemClickLister

    interface onItemClickLister {

        fun onItemClick(id: Int)
    }

    fun setOnItemClickListener(listener: onItemClickLister) {
        mListener = listener
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.card_accounts, viewGroup, false)
        return ViewHolder(v, mListener)

    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.itemTable.text = boards[i].getBoard()
        viewHolder.itemUser.text = boards[i].getCustomer()
        viewHolder.itemPrice.text = "$ " + boards[i].getTotalPrice()

    }

    override fun getItemCount(): Int {
        return boards.size
    }

    inner class ViewHolder(itemView: View, listener: onItemClickLister) :
        RecyclerView.ViewHolder(itemView) {
        var itemTable: TextView = itemView.findViewById(R.id.itemTable)
        var itemUser: TextView = itemView.findViewById(R.id.itemUser)
        var itemPrice: TextView = itemView.findViewById(R.id.itemPrice)

        init {

            itemView.setOnClickListener {
                listener.onItemClick(boards[adapterPosition].getId())
            }
        }
    }

    fun filter(txtSearch: String) {
        val lenght = txtSearch.length
        if (lenght == 0) {
            boards.clear()
            boards.addAll(boardsOriginal)
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                var list: MutableList<Board> = boards.stream()
                    .filter { i -> i.getCustomer().toLowerCase().contains(txtSearch.toLowerCase()) }.collect(
                        Collectors.toList())
                boards.clear()
                boards.addAll(list)
            } else {
                boards.forEach{b ->
                    if (b.getCustomer().toLowerCase().contains(txtSearch.toLowerCase())){
                        boards.add(b)
                    }}
            }
        }
        notifyDataSetChanged()
    }
}