package com.example.myapplication.testpaging

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityPagingBinding
import com.example.myapplication.databinding.ActivityTestPagingBinding
import com.example.myapplication.recyclerview.RecyclerViewAdapter
import kotlinx.coroutines.flow.collectLatest

class TestPagingActivity : AppCompatActivity() {

    lateinit var binding: ActivityTestPagingBinding
    lateinit var adapterPaging: PagingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestPagingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
        initViewModel()
    }


    private fun initRecyclerView() {
        binding.recyclerViewPagingData.apply {
            layoutManager = LinearLayoutManager(this@TestPagingActivity)
            val decoration  = DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL)
            addItemDecoration(decoration)
            adapterPaging = PagingAdapter()
            adapter = adapterPaging
        }
    }

    private fun initViewModel() {
        val viewModel = ViewModelProvider(this)[PagingActivityViewModel::class.java]
        lifecycleScope.launchWhenCreated {
            viewModel.getListData().collectLatest {
                adapterPaging.submitData(it)
            }
        }
    }

}