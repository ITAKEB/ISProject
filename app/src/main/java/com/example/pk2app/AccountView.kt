package com.example.pk2app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class AccountView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_view)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //  supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    // don't forget click listener for back button
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}