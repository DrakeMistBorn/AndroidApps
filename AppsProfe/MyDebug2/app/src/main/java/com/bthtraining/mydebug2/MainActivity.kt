package com.bthtraining.mydebug2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    private val TAG:String = "MyActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        for (i in 0..10) {
            Log.e(TAG, "index=" + i)
        }

        for (variable in 10..20) {
            println("Here $variable")
        }


    }

}