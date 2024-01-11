package com.example.pumpkinmarket

import android.content.Intent
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import com.example.pumpkinmarket.databinding.ActivityDetailBinding
import com.google.android.material.snackbar.Snackbar
import java.text.DecimalFormat

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
        this.onBackPressedDispatcher.addCallback(this, callback)
    }

    private fun setItemData() {
        val receivedItem = intent.getParcelableExtra<PumpkinItem>(Constants.PUMPKIN_ITEM)
        receivedItem?.let {
            binding.detailItemImg.setImageResource(it.itemImage)
            binding.detailItemSeller.text = it.itemSeller
            binding.detailItemAddress.text = it.itemAddress
            binding.detailItemTitle.text = it.itemTitle
            binding.detailItemDescription.text = it.itemDescription
            binding.detailItemPrice.text = DecimalFormat("#,###").format(it.itemPrice) + "원"
            isLiked = it.isLiked
            binding.detailItemLike.setImageResource(if (isLiked) R.drawable.heart_filled else R.drawable.heart)
            binding.detailItemLike.setOnClickListener {
                toggleLike()
            }
            binding.detailTempertureDescription.paintFlags = Paint.UNDERLINE_TEXT_FLAG
            binding.ivBack.setOnClickListener {
                exit()
            }
        }
    }

    private fun toggleLike() {
        isLiked = if (isLiked.not()) {
            binding.detailItemLike.setImageResource(R.drawable.heart_filled)
            Snackbar.make(binding.constraintBottomBar, "관심 목록에 추가되었습니다.", Snackbar.LENGTH_SHORT)
                .show()
            true
        } else {
            binding.detailItemLike.setImageResource(R.drawable.heart)
            false
        }
    }

    private fun exit() {
        val itemPosition = intent.getIntExtra(Constants.ITEM_POSITON, 0)
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra(Constants.ITEM_POSITON, itemPosition)
            putExtra(Constants.IS_LIKE, isLiked)
        }
        setResult(RESULT_OK, intent)
        if (isFinishing.not()) finish()
    }

    private val callback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            exit()
        }
    }
}