package com.example.practiceadapterview.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practiceadapterview.databinding.ItemRecyclerviewBinding

class MyAdapter(private val mItems: MutableList<MyItem>) : RecyclerView.Adapter<MyAdapter.Holder>() {

    interface ItemClick {
        fun onClick(view: View, position: Int)
    }

    var itemClick : ItemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.itemView.setOnClickListener {
            itemClick?.onClick(it, position)
        }

        holder.itemImage.setImageResource(mItems[position].itemImage)
        holder.itemName.text = mItems[position].itemName
        holder.itemNumber.text = mItems[position].itemNumber
    }

//    이 함수는 더 이상 필요하지 않은 듯 하다.
//    override fun getItemId(position: Int): Long {
//        return position.toLong()
//    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    inner class Holder(binding: ItemRecyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val itemImage = binding.imageItem
        val itemName = binding.textItem1
        val itemNumber = binding.textItem2
    }

}