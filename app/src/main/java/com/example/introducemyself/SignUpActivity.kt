package com.example.introducemyself

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val nameEditText = findViewById<EditText>(R.id.et_name)
        val ageEditText = findViewById<EditText>(R.id.et_signUpAge)
        val mbtiEditText = findViewById<EditText>(R.id.et_signUpMbti)
        val idEditText = findViewById<EditText>(R.id.et_signUpID)
        val pwdEditText = findViewById<EditText>(R.id.et_signUpPwd)
        val signUpBtn = findViewById<Button>(R.id.btn_signUp)

        signUpBtn.isEnabled = false
        nameEditText.doAfterTextChanged {
            signUpBtn.isEnabled = nameEditText.text.isNotEmpty() && ageEditText.text.isNotEmpty() && mbtiEditText.text.isNotEmpty() && idEditText.text.isNotEmpty() && pwdEditText.text.isNotEmpty()
        }
        ageEditText.doAfterTextChanged {
            signUpBtn.isEnabled = nameEditText.text.isNotEmpty() && ageEditText.text.isNotEmpty() && mbtiEditText.text.isNotEmpty() && idEditText.text.isNotEmpty() && pwdEditText.text.isNotEmpty()
        }
        mbtiEditText.doAfterTextChanged {
            signUpBtn.isEnabled = nameEditText.text.isNotEmpty() && ageEditText.text.isNotEmpty() && mbtiEditText.text.isNotEmpty() && idEditText.text.isNotEmpty() && pwdEditText.text.isNotEmpty()
        }
        idEditText.doAfterTextChanged {
            signUpBtn.isEnabled = nameEditText.text.isNotEmpty() && ageEditText.text.isNotEmpty() && mbtiEditText.text.isNotEmpty() && idEditText.text.isNotEmpty() && pwdEditText.text.isNotEmpty()
        }
        pwdEditText.doAfterTextChanged {
            signUpBtn.isEnabled = nameEditText.text.isNotEmpty() && ageEditText.text.isNotEmpty() && mbtiEditText.text.isNotEmpty() && idEditText.text.isNotEmpty() && pwdEditText.text.isNotEmpty()
        }

        signUpBtn.setOnClickListener {
            val inputId = idEditText.text.toString()
            val inputPwd = pwdEditText.text.toString()
            if (nameEditText.text.isEmpty() || ageEditText.text.isEmpty() || mbtiEditText.text.isEmpty() || pwdEditText.text.isEmpty() || idEditText.text.isEmpty()) {
                Toast.makeText(this, "빈칸 없이 모두 입력해주세요.", Toast.LENGTH_SHORT).show()
            } else {
                val newMember = MemberData(id = inputId, pwd = inputPwd, name = nameEditText.text.toString(), age = ageEditText.text.toString(), mbti = mbtiEditText.text.toString())
                MemberInfo.memberInfo.add(newMember)
                Toast.makeText( this,"가입 완료! 로그인 후 이용해주세요.", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, SignInActivity::class.java)
                intent.putExtra("memberId", inputId)
                intent.putExtra("memberPwd", inputPwd)
                setResult(RESULT_OK, intent)
                finish()
            }
        }
    }
}