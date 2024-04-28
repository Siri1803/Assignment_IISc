package com.example.splash_screen_assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar

class AnotherActvity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_another_actvity)

        val actionBar :  ActionBar? = supportActionBar
        actionBar!!.title  = "Another Activity"
    }
}