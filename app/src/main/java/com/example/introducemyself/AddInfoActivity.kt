package com.example.introducemyself

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts

class AddInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_info)

        val ageEditText = findViewById<EditText>(R.id.et_Age)
        val mbtiEditText = findViewById<EditText>(R.id.et_mbti)
        val addInfoBtn = findViewById<Button>(R.id.btn_addInfo)
        val name = intent.getStringExtra("memberName").toString()
        val id = intent.getStringExtra("memberId").toString()
        val pwd = intent.getStringExtra("memberPwd").toString()

        addInfoBtn.setOnClickListener {
            val age = ageEditText.text.toString()
            val mbti = mbtiEditText.text.toString()

            if(ageEditText.text.isEmpty()||mbtiEditText.text.isEmpty()) {
                Toast.makeText( this,"나이,MBTI 모두 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
            else {
                val newMember = MemberData(id = id, pwd = pwd, name = name, age = age, mbti = mbti)
                MemberInfo.memberInfo.add(newMember)
                Toast.makeText( this,"가입 완료! 로그인 후 이용해주세요.", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, SignInActivity::class.java)
                intent.putExtra("memberId", id)
                intent.putExtra("memberPwd", pwd)
                setResult(RESULT_OK, intent)
                startActivity(intent)
            }
        }
    }
}