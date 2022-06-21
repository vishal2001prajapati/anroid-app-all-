package com.example.myapplication.coroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.fragment.DemoTwoFragment

class CoroutineActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine)
        setFragment()

    }

    private fun setFragment() {
        val fragment = RecyclerViewFragment.newInstance()
        supportFragmentManager.beginTransaction().replace(android.R.id.content, fragment).commit()
    }
}