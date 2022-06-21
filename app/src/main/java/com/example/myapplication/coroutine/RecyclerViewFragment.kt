package com.example.myapplication.coroutine

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.coroutine.viewmodel.CoroutineViewModel

class RecyclerViewFragment : Fragment() {

    lateinit var recyclerViewAdapter: CoroutineAdapter
    lateinit var recyclerViewAdapterSecond: CoroutineAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_reycler_view, container, false)

        initViewModel(view)
        viewModel()
        return view
    }

    private fun initViewModel(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        val recyclerViewSecond = view.findViewById<RecyclerView>(R.id.recyclerViewSecond)
//        recyclerView.layoutManager = LinearLayoutManager(activity)
//        val decoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
//        recyclerView.addItemDecoration(decoration)
        recyclerViewAdapter = CoroutineAdapter()
        recyclerViewAdapterSecond = CoroutineAdapter()

        recyclerView.adapter = recyclerViewAdapter
        recyclerViewSecond.adapter = recyclerViewAdapterSecond
    }

    private fun viewModel() {
        val viewModel = ViewModelProvider(this)[CoroutineViewModel::class.java]
        viewModel.getRecyclerListObserver().observe(viewLifecycleOwner, Observer<CoroutineDataClassList> {
            if (it != null) {
                recyclerViewAdapter.setUpdatedData(it.items)
                recyclerViewAdapterSecond.setUpdatedData(it.items)
            } else {
                Toast.makeText(activity, "Error in getting data", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.apiCall()

    }

    companion object {
        @JvmStatic
        fun newInstance() = RecyclerViewFragment()
    }
}