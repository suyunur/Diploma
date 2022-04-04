package com.example.diploma.ext

import android.graphics.BitmapFactory
import android.widget.ImageView
import java.net.URL


fun ImageView.loadImage(url: String?) {
    Thread {
        val link = URL(url)
        val bmp = BitmapFactory.decodeStream(link.openConnection().getInputStream())
        this.setImageBitmap(bmp)
    }.start()
}