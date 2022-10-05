package com.globe.drawer

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.PersistableBundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity: AppCompatActivity() {

    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var buttonLogin: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        if(isLoggedIn()) {
            launchMainActivity()
            finish()
        }

        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        buttonLogin = findViewById(R.id.buttonLogin)

        buttonLogin.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                Log.d("LoginActivity", "Login button clicked")

                if(isCredentialsValid()) {
                    saveCredentials()
                    launchMainActivity()
                    finish()
                }

                else {
                    Toast.makeText(this@LoginActivity, "Incorrect email or password",
                        Toast.LENGTH_SHORT)
                        .show()
                }

            }
        })
    }
    private fun launchMainActivity(){
        val mainIntent = Intent(this, MainActivity::class.java)
        startActivity(mainIntent)
    }
    private fun isCredentialsValid(): Boolean {
        val email = etEmail.text.toString().trim()
        val password = etPassword.text.toString().trim()

        return email == "example@gmail.com" && password == "password"
        }

    private fun getMySharedPreference() : SharedPreferences{
        return getSharedPreferences(Constants.PREFERENCE_NAME, Context.MODE_PRIVATE)
    }

    private fun isLoggedIn() : Boolean {
        val sharedPreferences = getMySharedPreference()
        val email = sharedPreferences.getString(Constants.EMAIL, null)
        return email != null
    }
    private fun saveCredentials(){
       val sharedPreferences = getMySharedPreference()

        val email = etEmail.text.toString().trim()

        sharedPreferences
            .edit()
            .putString(Constants.EMAIL, email)
            .apply()


    }

}