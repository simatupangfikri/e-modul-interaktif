package com.app.e_modulinteraktif.adapter

import android.widget.ImageView

fun bindImage(imgView: ImageView, imgRes: Int?) {
    imgRes?.let {
        imgView.setImageResource(imgRes)
    }
}