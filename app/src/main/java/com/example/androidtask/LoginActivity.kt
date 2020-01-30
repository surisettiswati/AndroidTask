package com.example.androidtask

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast


class LoginActivity : Activity(), View.OnClickListener {

    private lateinit var txt_signIn: TextView
    private lateinit var edt_username: TextView
    private lateinit var edt_password: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setReference()
        setClickListeners()

    }


    private fun setClickListeners() {
        txt_signIn.setOnClickListener(this)

    }


    private fun setReference() {

        txt_signIn = findViewById(R.id.txt_signIn)
        edt_password = findViewById(R.id.edt_password)
        edt_username = findViewById(R.id.edt_username)


    }


    override fun onClick(v: View?) {

        when (v?.id) {
            R.id.txt_signIn -> Checkvalidations()


        }

    }

    private fun Checkvalidations() {

        if (edt_username.text.length == 0) {
            Toast.makeText(this, getString(R.string.enter_username), Toast.LENGTH_SHORT).show()
        } else if (edt_password.text.length == 0) {
            Toast.makeText(this, getString(R.string.enter_password), Toast.LENGTH_SHORT).show()
        } else {
            navigateToMain()
        }
    }


    private fun navigateToMain() {
        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
        finish()
    }

}
