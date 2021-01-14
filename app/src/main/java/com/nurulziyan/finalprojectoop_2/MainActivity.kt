package com.nurulziyan.finalprojectoop_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_nurul.setOnClickListener {
            startActivity(Intent(this,NurulActivity::class.java))
        }
        btn_ziyan.setOnClickListener {//pindah activity
            startActivity(Intent(this,ZiyanActivity::class.java))
        }
    }
}