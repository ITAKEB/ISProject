package com.example.pk2app

import Data.DataDbHelper
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pk2app.ui.AccountsAdapter
import com.example.pk2app.ui.ItemsAccountAdapter
import com.example.pk2app.ui.PopUpAddItemCustomer
import com.example.pk2app.ui.PopUpAreUSure
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AccountView: AppCompatActivity() {

    private lateinit var db: DataDbHelper
    private lateinit var adapter:ItemsAccountAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var boardId:Int = intent.extras?.get("boardId").toString().toInt()
        setContentView(R.layout.activity_account_view)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //  supportActionBar?.setDisplayShowHomeEnabled(true)

        db = DataDbHelper(this)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewItemsAccount)
        val dataItemBoards = db.getItemsBoardData()

        adapter = ItemsAccountAdapter(dataItemBoards)

        updateRecyclerView(recyclerView)


        val btAddItem = findViewById<FloatingActionButton>(R.id.btaddItemTable)

        btAddItem.setOnClickListener {
            PopUpAddItemCustomer(
                onSubmitClickListener = {itemBoard ->
                    Toast.makeText(this, "Usted ingreso: ${itemBoard.getItemTitle()}", Toast.LENGTH_SHORT).show()
                    db.insertItemBoard(boardId,itemBoard.getItemTitle(), itemBoard.getItemTotal(),itemBoard.getItemPrice(),itemBoard.getQuantity())

                    updateRecyclerView(recyclerView)

                    adapter.notifyDataSetChanged()
                }
            ).show(supportFragmentManager, "dialog")
        }

        val btPay = findViewById<MaterialButton>(R.id.btPay)

        btPay.setOnClickListener {
            PopUpAreUSure(
                onSubmitClickListener = {quantity ->
                    Toast.makeText(this,"Hola $quantity",Toast.LENGTH_SHORT).show()
                }
            ).show(supportFragmentManager, "dialog")
        }
    }


    private fun updateRecyclerView(recyclerView: RecyclerView) {
        val dataItemBoards = db.getItemsBoardData()

        adapter = ItemsAccountAdapter(dataItemBoards)
        recyclerView.layoutManager = GridLayoutManager(this, 1)
        recyclerView.adapter = adapter
    }

    // don't forget click listener for back button
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun createPopUp(){}

}