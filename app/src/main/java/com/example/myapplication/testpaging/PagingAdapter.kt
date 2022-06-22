package com.example.myapplication.testpaging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.apicalling.UserDetailsList
import com.example.myapplication.databinding.ItemPagingBinding
import com.example.myapplication.recyclerview.RecyclerViewAdapter

class PagingAdapter: PagingDataAdapter<NewUserDetailsList, MyViewHolderPaging>(DiffUtilCallBack()) {

    override fun onBindViewHolder(holder: MyViewHolderPaging, position: Int) {
        holder.bind(getItem(position)!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolderPaging {
        val view = ItemPagingBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolderPaging(view)
    }



    class DiffUtilCallBack : DiffUtil.ItemCallback<NewUserDetailsList>() {
        override fun areItemsTheSame(oldItem: NewUserDetailsList, newItem: NewUserDetailsList): Boolean {
            return oldItem.firstName == newItem.firstName
        }

        override fun areContentsTheSame(oldItem: NewUserDetailsList, newItem: NewUserDetailsList): Boolean {
            return oldItem.firstName == newItem.firstName && oldItem.lastName == newItem.lastName
        }

    }

}

class MyViewHolderPaging(val view: ItemPagingBinding) : RecyclerView.ViewHolder(view.root) {

    fun bind(data: NewUserDetailsList) {
        Glide.with(view.imagePagingItem.context).load(data.avatar).into(view.imagePagingItem)
        view.apply {
            textApiName.text = data.firstName + " " + data.lastName
        }

    }

}