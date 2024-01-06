package com.example.pumpkinmarket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pumpkinmarket.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        setAdapter()
    }

    private fun setAdapter() {
        val dataList = mutableListOf<PumpkinItem>()
        dataList.add(PumpkinItem(R.drawable.sample1, R.string.sample1_title, R.string.sample1_address, R.string.sample1_price, R.string.sample1_chat, R.string.sample1_like))
        dataList.add(PumpkinItem(R.drawable.sample2, R.string.sample2_title, R.string.sample2_address, R.string.sample2_price, R.string.sample2_chat, R.string.sample2_like))
        dataList.add(PumpkinItem(R.drawable.sample3, R.string.sample3_title, R.string.sample3_address, R.string.sample3_price, R.string.sample3_chat, R.string.sample3_like))
        dataList.add(PumpkinItem(R.drawable.sample4, R.string.sample4_title, R.string.sample4_address, R.string.sample4_price, R.string.sample4_chat, R.string.sample4_like))
        dataList.add(PumpkinItem(R.drawable.sample5, R.string.sample5_title, R.string.sample5_address, R.string.sample5_price, R.string.sample5_chat, R.string.sample5_like))
        dataList.add(PumpkinItem(R.drawable.sample6, R.string.sample6_title, R.string.sample6_address, R.string.sample6_price, R.string.sample6_chat, R.string.sample6_like))
        dataList.add(PumpkinItem(R.drawable.sample7, R.string.sample7_title, R.string.sample7_address, R.string.sample7_price, R.string.sample7_chat, R.string.sample7_like))
        dataList.add(PumpkinItem(R.drawable.sample8, R.string.sample8_title, R.string.sample8_address, R.string.sample8_price, R.string.sample8_chat, R.string.sample8_like))
        dataList.add(PumpkinItem(R.drawable.sample9, R.string.sample9_title, R.string.sample9_address, R.string.sample9_price, R.string.sample9_chat, R.string.sample9_like))
        dataList.add(PumpkinItem(R.drawable.sample10, R.string.sample10_title, R.string.sample10_address, R.string.sample10_price, R.string.sample10_chat, R.string.sample10_like))

        val adapter = PumpkinAdapter(dataList)

        binding.mainRecyclerView.adapter = adapter
        binding.mainRecyclerView.layoutManager = LinearLayoutManager(this)
    }
}