package com.example.pumpkinmarket

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pumpkinmarket.databinding.ItemRecyclerViewBinding

class PumpkinAdapter(private val mItems: MutableList<PumpkinItem>) :
    RecyclerView.Adapter<PumpkinAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PumpkinAdapter.Holder {
        val binding = ItemRecyclerViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: PumpkinAdapter.Holder, position: Int) {
        holder.itemImage.setImageResource(mItems[position].itemImage)
        holder.itemTitle.text = mItems[position].itemTitle.toString()
        holder.itemAddress.text = mItems[position].itemAddress.toString()
        holder.itemPrice.text = mItems[position].itemPrice.toString()
        holder.itemChat.text = mItems[position].itemChat.toString()
        holder.itemLike.text = mItems[position].itemLike.toString()
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    inner class Holder(binding: ItemRecyclerViewBinding) : RecyclerView.ViewHolder(binding.root) {
        val itemImage = binding.ivProductImg
        val itemTitle = binding.tvProductTitle
        val itemAddress = binding.tvProductAddress
        val itemPrice = binding.tvProductPrice
        val itemChat = binding.tvChatCount
        val itemLike = binding.tvLikeCount
    }
}