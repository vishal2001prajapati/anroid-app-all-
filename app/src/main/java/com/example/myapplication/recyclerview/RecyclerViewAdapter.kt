package com.example.myapplication.recyclerview

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.coroutine.CoroutineDataClass
import com.example.myapplication.databinding.ItemRecycletviewBinding

class RecyclerViewAdapter(var context: Context): ListAdapter<RecyclerViewDataClass, RecyclerViewAdapter.MyViewHolder>(DiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val liteItemBinding = ItemRecycletviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(liteItemBinding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val itemList = getItem(position)
        holder.bind(itemList)
        holder.binding.root.setOnClickListener {
            val intent = Intent(context, RecyclerViewDetailActivity::class.java).apply {
                putExtra("image", itemList.image)
                putExtra("title", itemList.name)
            }
            context.startActivity(intent)
        }

    }

    class DiffUtil : androidx.recyclerview.widget.DiffUtil.ItemCallback<RecyclerViewDataClass>() {
        override fun areItemsTheSame(oldItem: RecyclerViewDataClass, newItem: RecyclerViewDataClass): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: RecyclerViewDataClass, newItem: RecyclerViewDataClass): Boolean {
            return oldItem == newItem
        }

    }

    class MyViewHolder(val binding: ItemRecycletviewBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: RecyclerViewDataClass) {
            binding.apply {
                imageItem.setImageResource(item.image)
                textItem.text = item.name
            }
        }
    }

}