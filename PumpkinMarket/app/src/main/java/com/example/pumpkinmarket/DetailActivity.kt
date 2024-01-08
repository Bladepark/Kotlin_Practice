package com.example.pumpkinmarket

import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pumpkinmarket.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private val binding by lazy { ActivityDetailBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        setItemData()
        setBackBtn()
    }

    private fun setBackBtn() {
        binding.ivBack.setOnClickListener {
            if(isFinishing.not())finish()
        }
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
            binding.detailTempertureDescription.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        }
    }
}