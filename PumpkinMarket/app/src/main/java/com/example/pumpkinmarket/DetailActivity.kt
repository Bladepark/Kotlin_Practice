package com.example.pumpkinmarket

import android.content.Intent
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pumpkinmarket.databinding.ActivityDetailBinding
import com.google.android.material.snackbar.Snackbar

class DetailActivity : AppCompatActivity() {

    private val binding by lazy { ActivityDetailBinding.inflate(layoutInflater) }
    private var isLiked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        setItemData()
        setBackBtn()
    }
    private fun setItemData() {
        val receivedItem = intent.getParcelableExtra<PumpkinItem>("pumpkinItem")
        receivedItem?.let {
            binding.detailItemImg.setImageResource(it.itemImage)
            binding.detailItemSeller.text = it.itemSeller
            binding.detailItemAddress.text = it.itemAddress
            binding.detailItemTitle.text = it.itemTitle
            binding.detailItemDescription.text = it.itemDescription
            binding.detailItemPrice.text = it.itemPrice
            isLiked = it.isLiked
            binding.detailItemLike.setImageResource(if (isLiked) R.drawable.heart_filled else R.drawable.heart)
            binding.detailItemLike.setOnClickListener {
                if (isLiked.not()) {
                    binding.detailItemLike.setImageResource(R.drawable.heart_filled)
                    Snackbar.make(binding.constraintBottomBar, "관심 목록에 추가되었습니다.", Snackbar.LENGTH_SHORT).show()
                    isLiked = true
                } else {
                    binding.detailItemLike.setImageResource(R.drawable.heart)
                    isLiked = false
                }
            }
            binding.detailTempertureDescription.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        }
    }
    private fun setBackBtn() {
        binding.ivBack.setOnClickListener {
            val likePosition = intent.getIntExtra("likePosition", 0)
            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra("likePosition", likePosition)
                putExtra("isLiked", isLiked)
            }
            setResult(RESULT_OK, intent)
            if(isFinishing.not())finish()
        }
    }


}