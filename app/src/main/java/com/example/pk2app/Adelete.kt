package com.example.pk2app

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class Adelete : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawer: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adelete)

        supportFragmentManager.beginTransaction().replace(R.id.flContent, Delete()).commit()

        var toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbarMain)
        setSupportActionBar(toolbar)

        drawer = findViewById(R.id.drawerLayoutDelete)

        toggle = ActionBarDrawerToggle(
            this,
            drawer,
            toolbar,
            R.string.navigationDrawerOpen,
            R.string.navigationDrawerClose
        )
        drawer.addDrawerListener(toggle)

        toolbar.setNavigationIcon(R.drawable.ic_hamburmenu)

        supportActionBar?.setHomeButtonEnabled(true)

        val navigationView: NavigationView = findViewById(R.id.navViewDelete)
        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navHome -> {
                Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()
//                supportFragmentManager.beginTransaction().replace(R.id.flContent, Home()).commit()
                val newActivity = Intent(this, MainActivity::class.java)
                startActivity(newActivity)
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
            R.id.navItems -> {
                Toast.makeText(this, "Items", Toast.LENGTH_SHORT).show()
//                supportFragmentManager.beginTransaction().replace(R.id.flContent, Items()).commit()
                val newActivity = Intent(this, AItems::class.java)
                startActivity(newActivity)
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
            R.id.navDelete -> {
                Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show()
////                supportFragmentManager.beginTransaction().replace(R.id.flContent, Delete()).commit()
//                val newActivity = Intent(this, Adelete::class.java)
//                startActivity(newActivity)
//                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
            R.id.navPayments -> {
                Toast.makeText(this, "Payed Accounts", Toast.LENGTH_SHORT).show()
//                supportFragmentManager.beginTransaction().replace(R.id.flContent, PayedAccounts()).commit()
                val newActivity = Intent(this, AaccountsPayed::class.java)
                startActivity(newActivity)
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        }
        drawer.closeDrawer(GravityCompat.START, false)
        return true
    }
    override fun onPostCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onPostCreate(savedInstanceState, persistentState)
        toggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        toggle.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}