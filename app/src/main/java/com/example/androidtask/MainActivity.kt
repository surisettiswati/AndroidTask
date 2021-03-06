package com.example.androidtask

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var toolbar: ActionBar
    lateinit var view: View
    private lateinit var img_filter: ImageView
    private lateinit var img_menu: ImageView
    private lateinit var img_search: ImageView
    private lateinit var txt_title: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setCustomActionBar()
        setReference()
        setClickListeners()
        EnableAll()


    }

    private fun setCustomActionBar() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        supportActionBar!!.setDisplayShowCustomEnabled(false)
        supportActionBar!!.setCustomView(R.layout.custom_action_bar)
        supportActionBar!!.setDisplayShowCustomEnabled(true)
        toolbar = supportActionBar!!
        view = supportActionBar!!.customView
    }


    private fun setClickListeners() {
        img_menu.setOnClickListener(this)
        img_search.setOnClickListener(this)
        img_filter.setOnClickListener(this)

    }


    private fun setReference() {
        img_menu = view.findViewById(R.id.img_menu)
        txt_title = view.findViewById(R.id.txt_title)
        img_search = view.findViewById(R.id.img_search)
        img_filter = view.findViewById(R.id.img_filter)
    }


    fun EnableAll() {
        img_menu.visibility = View.VISIBLE
        txt_title.visibility = View.VISIBLE
        img_menu.visibility = View.VISIBLE
    }


    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }


    override fun onClick(v: View?) {
        when (v?.getId()) {
            R.id.img_menu -> Toast.makeText(this, "Menu Clicked", Toast.LENGTH_SHORT).show()
            R.id.img_filter -> Toast.makeText(this, "Filter Clicked", Toast.LENGTH_SHORT).show()
            R.id.img_search -> Toast.makeText(this, "Search Clicked", Toast.LENGTH_SHORT).show()
        }

    }


    private fun BackClicked() {
        val fm = supportFragmentManager
        if (fm.backStackEntryCount > 0) {
            fm.popBackStack()
        } else {
            finish()
        }

    }


}

