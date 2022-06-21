package com.example.myapplication.coroutine

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R


class CoroutineAdapter : RecyclerView.Adapter<CoroutineAdapter.MyViewHolderCoroutine>() {

    private var itemsData = ArrayList<CoroutineDataClass>()

    @SuppressLint("NotifyDataSetChanged")
    fun setUpdatedData(itemsData: ArrayList<CoroutineDataClass>) {
        this.itemsData = itemsData
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolderCoroutine {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_coroutine,parent,false)
        return  MyViewHolderCoroutine(view)
    }

    override fun onBindViewHolder(holder: MyViewHolderCoroutine, position: Int) {
        holder.bind(itemsData[position])
    }

    override fun getItemCount(): Int {
        return itemsData.size
    }

    class MyViewHolderCoroutine(view: View) : RecyclerView.ViewHolder(view) {

        val image = view.findViewById<ImageView>(R.id.item_coroutine_image)
        val title = view.findViewById<TextView>(R.id.item_coroutine_text)
        val description = view.findViewById<TextView>(R.id.item_coroutine_description_text)


        fun bind(data: CoroutineDataClass) {
            title.text = data.name
            description.text = data.description
            val url = data.owner.avatar_url
            Glide.with(image.context).load(url).into(image)
        }

    }


}