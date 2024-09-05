package com.example.jssapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class welcome_page : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.welcome_page)
        val btn_go : Button= findViewById<Button>(R.id.next_btn)
        btn_go.setOnClickListener {
            val intent= Intent(this,LogInActivity::class.java)
            startActivity(intent)
        }
    }
}