package com.app.e_modulinteraktif

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = "Profile"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        toolbar.setSubtitleTextColor(resources.getColor(android.R.color.white))
        toolbar.setTitleTextColor(resources.getColor(android.R.color.white))
        toolbar.setNavigationOnClickListener { finish() }
        val bio_mhs = findViewById<WebView>(R.id.biodata)
        bio_mhs.isHorizontalScrollBarEnabled = false
        bio_mhs.isVerticalScrollBarEnabled = false
        bio_mhs.loadUrl("file:///android_asset/mahasiswa.html")
        val mahasiswa = findViewById<ImageView>(R.id.mhsImage)
        Glide.with(applicationContext)
            .load(R.drawable.foto)
            .circleCrop()
            .into(mahasiswa)
    }

    override fun onPause() {
        super.onPause()
        finish()
    }
}