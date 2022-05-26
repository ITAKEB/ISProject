package com.example.pk2app

import Data.DataDbHelper
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
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
    private var id:Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        id = intent.extras?.get("boardId").toString().toInt()
        setContentView(R.layout.activity_account_view)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //  supportActionBar?.setDisplayShowHomeEnabled(true)

        db = DataDbHelper(this)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewItemsAccount)
        val totalAccount = findViewById<TextView>(R.id.totalAccount)

        val dataItemBoards = db.getItemsBoardData(id)

        adapter = ItemsAccountAdapter(dataItemBoards, db, totalAccount)

        updateRecyclerView(recyclerView,totalAccount)

        val btAddItem = findViewById<FloatingActionButton>(R.id.btaddItemTable)

        btAddItem.setOnClickListener {
            PopUpAddItemCustomer(
                onSubmitClickListener = {itemBoard ->
                    Toast.makeText(this, "Usted ingreso: ${itemBoard.getItemTitle()},id: ${id}", Toast.LENGTH_SHORT).show()
                    db.insertItemBoard(id,itemBoard.getItemTitle(), itemBoard.getItemTotal(),itemBoard.getItemPrice(),itemBoard.getQuantity())
//                    db.close()

                    updateRecyclerView(recyclerView,totalAccount)

                    adapter.notifyDataSetChanged()
                }
            ).show(supportFragmentManager, "dialog")
        }

        val btPay = findViewById<MaterialButton>(R.id.btPay)

        btPay.setOnClickListener {
            PopUpAreUSure(
                onSubmitClickListener = {
                    val board:Board = db.getBoard(id)[0]

                    Toast.makeText(this,"Hola ${board.getId()}",Toast.LENGTH_SHORT).show()
                    db.insertPayedBoard(board.getId(),board.getBoard(),board.getCustomer(),board.getTotalPrice())

                    //Aquí se debería eliminar la cuenta de Accounts (home)
                    db.deleteBoard(id)

                    val newActivity = Intent(this, MainActivity::class.java)
                    startActivity(newActivity)



                }
            ).show(supportFragmentManager, "dialog")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        db.updateTotalPriceBoard(id,adapter.getTotalPrice())

    }


    private fun updateRecyclerView(recyclerView: RecyclerView, totalAccount: TextView) {
        val dataItemBoards = db.getItemsBoardData(id)

        adapter = ItemsAccountAdapter(dataItemBoards,db, totalAccount)
        recyclerView.layoutManager = GridLayoutManager(this, 1)
        recyclerView.adapter = adapter
    }

    // don't forget click listener for back button
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


}