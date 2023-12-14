package com.example.introducemyself

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val mbtiEditText = findViewById<EditText>(R.id.et_mbti)
        val nameEditText = findViewById<EditText>(R.id.et_name)
        val idEditText = findViewById<EditText>(R.id.et_signUpID)
        val pwdEditText = findViewById<EditText>(R.id.et_signUpPwd)
        val pwdCheckEditText = findViewById<EditText>(R.id.et_signUpPwdCheck)
        val signUpBtn = findViewById<Button>(R.id.btn_signUp)

        signUpBtn.setOnClickListener {
            val inputMbti = mbtiEditText.text.toString()
            val inputName = nameEditText.text.toString()
            val inputId = idEditText.text.toString()
            val inputPwd = pwdEditText.text.toString()
            if(mbtiEditText.text.isEmpty() || nameEditText.text.isEmpty() || pwdEditText.text.isEmpty() || pwdCheckEditText.text.isEmpty() || idEditText.text.isEmpty()) {
                Toast.makeText( this,"이름, 아이디, 비밀번호, 비밀번호 확인란 모두 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
            else {
                val newMember = MemberData(id = inputId, pwd = inputPwd, name = inputName, mbti = inputMbti)
                MemberInfo.memberInfo.add(newMember)
                Toast.makeText( this,"가입 완료! 로그인 후 이용해주세요.", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, SignInActivity::class.java)
                intent.putExtra("memberId",inputId)
                intent.putExtra("memberPwd",inputPwd)
                setResult(RESULT_OK, intent)
                finish()
            }
        }
    }
}