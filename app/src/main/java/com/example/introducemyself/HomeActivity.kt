package com.example.introducemyself

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import kotlin.random.Random

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val homeImgView = findViewById<ImageView>(R.id.img_home)
        val idTextView = findViewById<TextView>(R.id.tv_homeId)
        val nameTextView = findViewById<TextView>(R.id.tv_homeName)
        val mbtiTextView = findViewById<TextView>(R.id.tv_homeMbti)
        val homeExitBtn = findViewById<Button>(R.id.btn_homeExit)

        val id = intent.getStringExtra("memberId")
        val pwd = intent.getStringExtra("memberPwd")

        val memberInfo = MemberInfo.memberInfo.find { it.id == id && it.pwd == pwd }

        val imgResources = arrayOf(R.drawable.bbangbbang1,R.drawable.bbangbbang2,R.drawable.bbangbbang3,
            R.drawable.bbangbbang4,R.drawable.bbangbbang5,R.drawable.bbangbbang6,R.drawable.bbangbbang7)

        val random = Random.nextInt(imgResources.size)

        homeImgView.setImageResource(imgResources[random])
        idTextView.setText("아이디 :" + memberInfo?.id)
        nameTextView.setText("이름 :" + memberInfo?.name)
        mbtiTextView.setText("MBTI :" + memberInfo?.mbti)

        homeExitBtn.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            intent.putExtra("memberId", id)
            intent.putExtra("memberPwd", pwd)
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}