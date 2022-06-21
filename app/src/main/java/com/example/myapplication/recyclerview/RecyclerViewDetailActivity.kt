package com.example.myapplication.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityRecyclerViewDetailBinding

class RecyclerViewDetailActivity : AppCompatActivity() {

    lateinit var dataBinding: ActivityRecyclerViewDetailBinding

//    lateinit var image_detail: AppCompatImageView
//    lateinit var text_detail: AppCompatTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_recycler_view_detail)

//        image_detail = findViewById(R.id.image_detail)
//        text_detail = findViewById(R.id.text_detail)

        val imageBanner = intent.getIntExtra("image",0)
        val titleName = intent.getStringExtra("title")

        dataBinding.imageDetail.setImageResource(imageBanner)
        dataBinding.textDetail.text = titleName

    }
}