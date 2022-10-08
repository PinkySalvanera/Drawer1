package com.globe.drawer

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.drawerlayout.widget.DrawerLayout
import com.globe.drawer.tipcalculator.CalculatorActivity
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var tvEmail: TextView
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigationView = findViewById(R.id.navView)
        navigationView.setNavigationItemSelectedListener(this)

        tvEmail = navigationView.getHeaderView(0).findViewById(R.id.tvEmail)
        drawerLayout = findViewById(R.id.drawerLayout)
        actionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout,
        R.string.open, R.string.close)

        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val email = getSharedPreferences(Constants.PREFERENCE_NAME, Context.MODE_PRIVATE)
            .getString(Constants.EMAIL, null)
        tvEmail.text = email
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            Log.d("Main Activity", "${item.title}")
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_calculator->{
                Log.d("Main Activity", "Calculator")
                val calcIntent = Intent(this, CalculatorActivity::class.java)
                startActivity(calcIntent)
                }

            R.id.action_movies->{
                Log.d("Main Activity", "Movies")
                }

            R.id.action_logout -> {
                Log.d("Main Activity", "Logout")
                AlertDialog.Builder(this)
                    .setTitle("LOGOUT")
                    .setMessage("Press OK to logout or CANCEL to continue session")
                    .setPositiveButton("OK", object : DialogInterface.OnClickListener {
                        override fun onClick(dialog: DialogInterface?, which: Int) {
                            val email = getSharedPreferences(Constants.PREFERENCE_NAME,
                                Context.MODE_PRIVATE)

                            email.edit()
                                .remove(Constants.EMAIL)
                                .apply()

                            val loginIntent = Intent(this@MainActivity,  LoginActivity::class.java)
                            startActivity(loginIntent)
                            finish()
                        }
                    })
                    .setNegativeButton("Cancel", object : DialogInterface.OnClickListener {
                        override fun onClick(dialog: DialogInterface?, which: Int) {
                        }

                    }).show()

                }
            }
        return false
        }

    }

