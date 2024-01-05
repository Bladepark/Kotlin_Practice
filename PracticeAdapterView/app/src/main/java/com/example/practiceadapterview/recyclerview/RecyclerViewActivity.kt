package com.example.practiceadapterview.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practiceadapterview.R
import com.example.practiceadapterview.databinding.ActivityRecyclerViewBinding

class RecyclerViewActivity : AppCompatActivity() {

    /*
       RecyclerView 사용을 위해 필요한 2가지
       1) Adapter
        - Adapter란 데이터 테이블을 목록 형태로 보여주기 위해 사용되는 것으로, 데이터를 다양한 형식의 리스트 형식을 보여주기 위해서 데이터와 RecyclerView 사이에 존재하는 객체이다.
        - 즉 데이터와 RecyclerView 사이의 통신을 위한 연결체이다.

       2) ViewHolder
        - ViewHolder란 화면에 표시될 데이터나 아이템들을 저장하는 역할 입니다.
        - RecyclerView의 개념을 적용하기위해선 스크롤 해서 위로 올라간 View를 재활용하기 위해서 이 View를 기억하고 있어야 합니다. ViewHolder가 그역할을 합니다.
     */

    private lateinit var binding: ActivityRecyclerViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataList = mutableListOf<MyItem>()
        dataList.add(MyItem(R.drawable.alarm, "alarm", "1"))
        dataList.add(MyItem(R.drawable.arrow, "arrow", "2"))
        dataList.add(MyItem(R.drawable.chat, "chat", "3"))
        dataList.add(MyItem(R.drawable.heart, "heart", "4"))
        dataList.add(MyItem(R.drawable.lv1, "lv1", "5"))
        dataList.add(MyItem(R.drawable.lv2, "lv2", "6"))
        dataList.add(MyItem(R.drawable.lv3, "lv3", "7"))
        dataList.add(MyItem(R.drawable.lv4, "lv4", "8"))

        val adapter = MyAdapter(dataList)

        binding.RecyclerView.adapter = adapter
        binding.RecyclerView.layoutManager = LinearLayoutManager(this)

        adapter.itemClick = object : MyAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {
                val name : String = dataList[position].itemName
                Toast.makeText(this@RecyclerViewActivity, "$name 이 선택되었습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}