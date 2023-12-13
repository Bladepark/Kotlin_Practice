package com.example.introducemyself

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts

class SignInActivity : AppCompatActivity() {
    lateinit var resultLauncher: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val idEditText = findViewById<EditText>(R.id.et_signInID)
        val pwdEditText = findViewById<EditText>(R.id.et_signInPwd)
        val signInBtn = findViewById<Button>(R.id.btn_signIn)
        val signUpBtn = findViewById<Button>(R.id.btn_signUp)

        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                // 결과가 성공인 경우 처리
                val data: Intent? = result.data
                val id = data?.getStringExtra("memberId")
                val pwd = data?.getStringExtra("memberPwd")

                idEditText.setText(id)
                pwdEditText.setText(pwd)
            }
        }

        signInBtn.setOnClickListener {
            val inputId = idEditText.text.toString()
            val inputPwd = pwdEditText.text.toString()
            if(idEditText.text.isEmpty()||pwdEditText.text.isEmpty()){
                Toast.makeText( this,"아이디와 비밀번호 모두 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
            else {
                val memberExist = MemberInfo.memberInfo.any { it.id == inputId && it.pwd == inputPwd}
                if(memberExist){
                    Toast.makeText( this,"로그인 성공!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, HomeActivity::class.java)
                    intent.putExtra("memberId", inputId)
                    intent.putExtra("memberPwd", inputPwd)
                    resultLauncher.launch(intent)
                }else{
                    Toast.makeText( this,"아이디 또는 비밀번호가 일치하지 않습니다. 회원이 아니시라면 회원가입을 먼저 해주세요.", Toast.LENGTH_SHORT).show()
                }
            }
        }

        signUpBtn.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}