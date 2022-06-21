package com.example.myapplication.apicalling

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemApiCapllingBinding

class ApiAdapter(private val list: ArrayList<Data>): RecyclerView.Adapter<ApiAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemApiCapllingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(private val itemViewBinding: ItemApiCapllingBinding) : RecyclerView.ViewHolder(itemViewBinding.root) {

        fun bind(data: Data) {
            itemViewBinding.apply {
                textId.text = data.id.toString()
                textName.text = data.name
                textYear.text = data.year.toString()
            }
        }

    }

}