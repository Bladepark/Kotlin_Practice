package com.example.pumpkinmarket

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
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
        holder.itemTitle.text = mItems[position].itemTitle
        holder.itemAddress.text = mItems[position].itemAddress
        holder.itemPrice.text = mItems[position].itemPrice
        holder.itemChat.text = mItems[position].itemChat
        holder.itemLike.text = mItems[position].itemLike
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

class PumpkinAdapterDecoration : RecyclerView.ItemDecoration() {
    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)

        val paint = Paint()
        paint.color = Color.GRAY

        for (i in 0 until parent.childCount) {
            val child = parent.getChildAt(i)
            val layoutParams = child.layoutParams as RecyclerView.LayoutParams
            val top = (child.bottom + layoutParams.bottomMargin + 10).toFloat()
            val bottom = top + 1f

            val left = parent.paddingStart.toFloat()
            val right = (parent.width - parent.paddingEnd).toFloat()

            c.drawRect(left, top, right, bottom, paint)
        }
    }
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        val offset = 10
        outRect.top = offset
        outRect.bottom = offset
    }
}