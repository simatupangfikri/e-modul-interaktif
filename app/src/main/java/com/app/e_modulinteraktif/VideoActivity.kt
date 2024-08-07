package com.app.e_modulinteraktif

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.appcompat.widget.Toolbar

class VideoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = "Video Materi"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        toolbar.setSubtitleTextColor(resources.getColor(R.color.white))
        toolbar.setTitleTextColor(resources.getColor(R.color.white))
        toolbar.setNavigationOnClickListener { finish() }
        val webView = findViewById<WebView>(R.id.video)
        webView.isHorizontalScrollBarEnabled = false
        webView.isVerticalScrollBarEnabled = false
        webView.webChromeClient = WebChromeClient()
        webView.settings.setJavaScriptEnabled(true)
        webView.loadUrl("file:///android_asset/video.html")
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}