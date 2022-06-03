package com.example.pk2app

import Data.DataDbHelper
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pk2app.ui.*
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AccountView() : AppCompatActivity() {

    private lateinit var db: DataDbHelper
    private lateinit var adapter: ItemsAccountAdapter
    private var id: Int = 0
    private var bool: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        id = intent.extras?.get("boardId").toString().toInt()
        bool = intent.extras?.get("payedAccount").toString().toInt()
        setContentView(R.layout.activity_account_view)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //  supportActionBar?.setDisplayShowHomeEnabled(true)

        db = DataDbHelper(this)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewItemsAccount)
        val totalAccount = findViewById<TextView>(R.id.totalAccount)

        val dataItemBoards = db.getItemsBoardData(id)

        adapter = ItemsAccountAdapter(dataItemBoards, db, totalAccount, bool)

        updateRecyclerView(recyclerView, totalAccount)

        val btAddItem = findViewById<FloatingActionButton>(R.id.btaddItemTable)

        btAddItem.setOnClickListener {
            PopUpAddItemCustomer(
                onSubmitClickListener = { itemBoard ->
                    Toast.makeText(
                        this,
                        "Usted ingresó: ${itemBoard.getItemTitle()}",
                        Toast.LENGTH_SHORT
                    ).show()
                    db.insertItemBoard(
                        id,
                        itemBoard.getItemId(),
                        itemBoard.getItemTitle(),
                        itemBoard.getItemDescription(),
                        itemBoard.getItemTotal(),
                        itemBoard.getItemPrice(),
                        itemBoard.getQuantity()
                    )
//                    db.close()

                    updateRecyclerView(recyclerView, totalAccount)

                    adapter.notifyDataSetChanged()
                },
                db.getItemData()
            ).show(supportFragmentManager, "dialog")
        }

        val btPay = findViewById<MaterialButton>(R.id.btPay)

        btPay.setOnClickListener {
            PopUpAreUSure(
                onSubmitClickListener = {
                    db.updateTotalPriceBoard(id, adapter.getTotalPrice())
                    val board: Board = db.getBoard(id)[0]

//                    Toast.makeText(this,"Hola ${board.getId()}",Toast.LENGTH_SHORT).show()
                    db.insertPayedBoard(
                        board.getId(),
                        board.getBoard(),
                        board.getCustomer(),
                        board.getTotalPrice()
                    )

                    //Aquí se debería eliminar la cuenta de Accounts (home)
                    db.deleteBoard(id)


                    val newActivity = Intent(this, MainActivity::class.java)
                    startActivity(newActivity)

                }
            ).show(supportFragmentManager, "dialog")
        }


        if (bool == 1) {
            btPay.visibility = View.INVISIBLE
            btAddItem.visibility = View.INVISIBLE
        }
    }


    private fun updateRecyclerView(recyclerView: RecyclerView, totalAccount: TextView) {
        val dataItemBoards = db.getItemsBoardData(id)

        adapter = ItemsAccountAdapter(dataItemBoards, db, totalAccount, bool)
        recyclerView.layoutManager = GridLayoutManager(this, 1)
        adapter.setOnBtInfoClickListener(object : ItemsAccountAdapter.onBtClickLister {
            override fun onBtClick(itemBoard: ItemBoard) {
                PopUpItemInfo(
                    itemBoard
                ).show(supportFragmentManager, "dialog")
            }
        })

        recyclerView.adapter = adapter
    }


    // don't forget click listener for back button
    override fun onSupportNavigateUp(): Boolean {
        db.updateTotalPriceBoard(id, adapter.getTotalPrice())
        if (bool == 0) {
            val newActivity = Intent(this, MainActivity::class.java)
            startActivity(newActivity)
            overridePendingTransition(0, 0);
        } else {
            val newActivity = Intent(this, MainActivity::class.java)
            newActivity.putExtra("key", 1)
            startActivity(newActivity)
            overridePendingTransition(0, 0);

        }

        return true
    }

    override fun onBackPressed() {
        onSupportNavigateUp()
    }


}