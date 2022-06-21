package com.example.myapplication

import android.widget.ImageView
import androidx.databinding.BindingAdapter

    @BindingAdapter("loadUrl")
    fun ImageView.loadUrl(profileImage: Int) {
        this.loadUrl(profileImage)
    }