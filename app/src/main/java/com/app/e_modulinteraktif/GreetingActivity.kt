package com.app.e_modulinteraktif

import android.app.Dialog
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import com.app.e_modulinteraktif.adapter.MainAdapter
import com.app.e_modulinteraktif.model.Menu

class GreetingActivity : AppCompatActivity() {

    lateinit var mulai: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_greeting)


        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        mulai = findViewById(R.id.mulai)

        mulai.setOnClickListener{
            startActivity(Intent(this@GreetingActivity, MainActivity::class.java))
        }
    }
}