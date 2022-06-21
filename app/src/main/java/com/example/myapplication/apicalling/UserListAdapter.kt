package com.example.myapplication.apicalling

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.ItemUserlistBinding

class UserListAdapter(private val list: ArrayList<UserDetailsList>): RecyclerView.Adapter<UserListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val userListBinding = ItemUserlistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(userListBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(private val itemViewBinding: ItemUserlistBinding): RecyclerView.ViewHolder(itemViewBinding.root) {

        fun bind(data: UserDetailsList) {
           Glide.with(itemViewBinding.imageApiItem.context).load(data.avatar).into(itemViewBinding.imageApiItem)
            itemViewBinding.apply {
                textApiId.text = data.id.toString()
                textApiName.text = data.firstName + " " + data.lastName
            }

        }

    }
}