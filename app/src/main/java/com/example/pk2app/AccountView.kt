package com.example.pk2app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AccountView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_view)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //  supportActionBar?.setDisplayShowHomeEnabled(true)


        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewItemsAccount)
        var adapter = ItemsAccountAdapter()
        recyclerView.layoutManager = GridLayoutManager(this, 1)
        recyclerView.adapter = adapter

        val btAddItem = findViewById<FloatingActionButton>(R.id.btaddItemTable)

        btAddItem.setOnClickListener {
            PopUpAddItemCustomer(
                onSubmitClickListener = {quantity ->
                    Toast.makeText(this, "Usted ingreso: $quantity", Toast.LENGTH_SHORT).show()
                }
            ).show(supportFragmentManager, "dialog")
        }
    }

    // don't forget click listener for back button
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun createPopUp(){}

}