package com.example.myapplication.recyclerview

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityRecyclerViewBinding

class RecyclerViewActivity : AppCompatActivity() {

    lateinit var binding: ActivityRecyclerViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_recycler_view)

        val adapter = RecyclerViewAdapter(this)
        val dataOne = RecyclerViewDataClass(1, R.drawable.ic_android, "Vishal")
        val dataTwo = RecyclerViewDataClass(2, R.drawable.ic_launcher_foreground, "ABC")
        val dataThree = RecyclerViewDataClass(3, R.drawable.ic_launcher_background, "DEF")
        val data = arrayListOf(dataOne, dataTwo, dataThree)
        binding.recyclerViewList.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        binding.recyclerViewList.adapter = adapter
        adapter.submitList(data)

        Handler(Looper.getMainLooper()).postDelayed({
            val dataThree = RecyclerViewDataClass(3, R.drawable.ic_launcher_foreground, "ghg")
            val dataFive = RecyclerViewDataClass(5, R.drawable.ic_launcher_background, "rtrty")
            val dataSix = RecyclerViewDataClass(6, R.drawable.ic_launcher_foreground, "ABC")
            val dataSeven = RecyclerViewDataClass(7, R.drawable.ic_android, "uyuy")
            val dataFour = RecyclerViewDataClass(4, R.drawable.ic_android, "ABC")
            val updatedData = arrayListOf<RecyclerViewDataClass>()
            updatedData.addAll(data)
            updatedData.apply {
                add(dataThree)
                add(dataFour)
                add(dataFive)
                add(dataSix)
                add(dataSeven)
            }
            adapter.submitList(updatedData)
        }, 1000)
    }
}