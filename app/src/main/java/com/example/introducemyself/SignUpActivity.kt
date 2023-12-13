package com.example.introducemyself

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val nameEditText = findViewById<EditText>(R.id.et_name)
        val idEditText = findViewById<EditText>(R.id.et_signUpID)
        val pwdEditText = findViewById<EditText>(R.id.et_signUpPwd)
        val signUpBtn = findViewById<Button>(R.id.btn_signUp)

        signUpBtn.setOnClickListener {
            val inputName = nameEditText.text.toString()
            val inputId = idEditText.text.toString()
            val inputPwd = pwdEditText.text.toString()
            if(nameEditText.text.isEmpty()||pwdEditText.text.isEmpty()||idEditText.text.isEmpty()){
                Toast.makeText( this,"이름, 아이디, 비밀번호 모두 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
            else {
                val intent = Intent(this, AddInfoActivity::class.java)
                intent.putExtra("memberName",inputName)
                intent.putExtra("memberId",inputId)
                intent.putExtra("memberPwd",inputPwd)
                startActivity(intent)
            }
        }




    }
}