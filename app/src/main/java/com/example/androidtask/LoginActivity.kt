package com.example.androidtask

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast


class LoginActivity : Activity(), View.OnClickListener {

    private lateinit var txt_signin: TextView
    private lateinit var edt_username: TextView
    private lateinit var edt_password: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setReference()
        setClickListeners()

    }


    private fun setClickListeners() {
        txt_signin.setOnClickListener(this)

    }


    private fun setReference() {

        txt_signin = findViewById(R.id.txt_signin)
        edt_password = findViewById(R.id.edt_password)
        edt_username = findViewById(R.id.edt_username)


    }


    override fun onClick(v: View?) {

        when (v?.getId()) {
            R.id.txt_signin -> Checkvalidations()


        }

    }

    private fun Checkvalidations() {

        if (edt_username.text.length == 0) {
            Toast.makeText(this, "Please enter Username", Toast.LENGTH_SHORT).show()
        } else if (edt_password.text.length == 0) {
            Toast.makeText(this, "Please enter Password", Toast.LENGTH_SHORT).show()
        } else {
            navigateToMain()
        }
    }


    private fun navigateToMain() {
        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
        finish()
    }

}
