package com.example.practiceadapterview.gridview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.practiceadapterview.databinding.ActivityGridViewBinding

class GridViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGridViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGridViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

//      간단한 string Array 타입 어댑터
//        val items = arrayOf<String?>("item1", "item2", "item3", "item4", "item5", "item6", "item7", "item8", "item9", "item10", "item11", "item12", "item13", "item14", "item15", "item16", "item17", "item18",  "item19", "item20")
//
//        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
//
//        binding.GridView.adapter = adapter

//      이미지 뷰 타입의 어댑터
        binding.GridView.adapter = ImageAdapter()

//      이미지 뷰 클릭 시 해당 뷰의 포지션 메세지 표시
        binding.GridView.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(this,"" + (position + 1) + "번째 선택", Toast.LENGTH_SHORT).show()
        }
    }
}