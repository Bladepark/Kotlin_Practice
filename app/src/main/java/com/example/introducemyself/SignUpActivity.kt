package com.example.introducemyself

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.internal.TextWatcherAdapter

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val nameEditText = findViewById<EditText>(R.id.nameTextInputEditText)
        val ageEditText = findViewById<EditText>(R.id.ageTextInputEditText)
        val mbtiEditText = findViewById<EditText>(R.id.mbtiTextInputEditText)
        val idEditText = findViewById<EditText>(R.id.idTextInputEditText)
        val pwdEditText = findViewById<EditText>(R.id.pwdTextInputEditText)
        val pwdCheckEditText = findViewById<EditText>(R.id.pwdCheckTextInputEditText)
        val signUpBtn = findViewById<Button>(R.id.btn_signUp)

        var nameFlag = false
        var ageFlag = false
        var mbtiFlag = false
        var idFlag = false
        var pwdFlag = false
        var pwdCheckFlag = false

        nameEditText.addTextChangedListener(@SuppressLint("RestrictedApi")
        object : TextWatcherAdapter() {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                nameFlag = isValidName()
                signUpBtn.isEnabled = (nameFlag && ageFlag && mbtiFlag && idFlag && pwdFlag && pwdCheckFlag)
            }
        })
        ageEditText.addTextChangedListener(@SuppressLint("RestrictedApi")
        object : TextWatcherAdapter() {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                ageFlag = isValidAge()
                signUpBtn.isEnabled = (nameFlag && ageFlag && mbtiFlag && idFlag && pwdFlag && pwdCheckFlag)
            }
        })
        mbtiEditText.addTextChangedListener(@SuppressLint("RestrictedApi")
        object : TextWatcherAdapter() {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                mbtiFlag = isValidMbti()
                signUpBtn.isEnabled = (nameFlag && ageFlag && mbtiFlag && idFlag && pwdFlag && pwdCheckFlag)
            }
        })
        idEditText.addTextChangedListener(@SuppressLint("RestrictedApi")
        object : TextWatcherAdapter() {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                idFlag = isValidId()
                signUpBtn.isEnabled = (nameFlag && ageFlag && mbtiFlag && idFlag && pwdFlag && pwdCheckFlag)
            }
        })
        pwdEditText.addTextChangedListener(@SuppressLint("RestrictedApi")
        object : TextWatcherAdapter() {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                pwdEditText.hint = "8자리 이상, 영문, 숫자, 특수문자 포함"
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                pwdFlag = isValidPwd()
                signUpBtn.isEnabled = (nameFlag && ageFlag && mbtiFlag && idFlag && pwdFlag && pwdCheckFlag)
            }
        })
        pwdCheckEditText.addTextChangedListener(@SuppressLint("RestrictedApi")
        object : TextWatcherAdapter() {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                pwdCheckFlag = isValidPwdCheck(pwdEditText.text.toString())
                signUpBtn.isEnabled = (nameFlag && ageFlag && mbtiFlag && idFlag && pwdFlag && pwdCheckFlag)
            }
        })

        signUpBtn.setOnClickListener {
            val inputId = idEditText.text.toString()
            val inputPwd = pwdEditText.text.toString()

            val newMember = MemberData(
                id = inputId,
                pwd = inputPwd,
                name = nameEditText.text.toString(),
                age = ageEditText.text.toString(),
                mbti = mbtiEditText.text.toString()
            )

            MemberInfo.memberInfo.add(newMember)
            Toast.makeText(this, "가입 완료! 로그인 후 이용해주세요.", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, SignInActivity::class.java)
            intent.putExtra("memberId", inputId)
            intent.putExtra("memberPwd", inputPwd)
            setResult(RESULT_OK, intent)
            finish()
        }
    }

    fun isValidName() : Boolean {
        val nameEditText = findViewById<EditText>(R.id.nameTextInputEditText)
        val name = nameEditText.text.toString()
        val namePattern = Regex("^[a-zA-Z가-힣]*\$")
        return if (nameEditText.text.isEmpty()) {
            nameEditText.error = "이름을 입력해주세요."
            false
        } else if (!name.matches(namePattern)){
            nameEditText.error = "이름에 숫자 또는 특수 문자가 들어갈 수 없습니다."
            false
        } else {
            nameEditText.error = null
            true
        }
    }

    fun isValidAge(): Boolean {
        val ageEditText = findViewById<EditText>(R.id.ageTextInputEditText)
        val age = ageEditText.text.toString().trim()
        val ageInt = if (age.isNotEmpty()) age.toInt() else -1
        val agePattern = Regex("^[0-9]*\$")
        return when {
            age.isEmpty() -> {
                ageEditText.error = "나이를 입력해주세요."
                false
            }
            !age.matches(agePattern) -> {
                ageEditText.error = "숫자만 입력해주세요."
                false
            }
            ageInt in 15..100 -> {
                ageEditText.error = null
                true
            }
            else -> {
                ageEditText.error = "만 14세 이상이어야 하며 100세 이하로 입력가능합니다."
                false
            }
        }
    }


    fun isValidMbti() : Boolean {
        val mbtiEditText = findViewById<EditText>(R.id.mbtiTextInputEditText)
        val mbti = mbtiEditText.text.toString()
        val mbtiPattern = Regex("[EI][NS][FT][JP]")
        return if (mbtiEditText.text.isEmpty()) {
            mbtiEditText.error = "MBTI를 입력해주세요."
            false
        } else if (!mbti.matches(mbtiPattern)){
            mbtiEditText.error = "MBTI 형식 예시 [E/I] [N/S] [F/T] [J/] 중 조합 -> ENFJ"
            false
        } else {
            mbtiEditText.error = null
            true
        }
    }

    fun isValidId() : Boolean {
        val idEditText = findViewById<EditText>(R.id.idTextInputEditText)
        val id = idEditText.text.toString()
        val idPattern = Regex("^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}\$")
        return if (idEditText.text.isEmpty()) {
            idEditText.error = "이메일을 입력해주세요."
            false
        } else if (!id.matches(idPattern)){
            idEditText.error = "이메일 형식으로 입력해주세요 -> example@example.com"
            false
        } else {
            idEditText.error = null
            true
        }
    }
    fun isValidPwd() : Boolean {
        val pwdEditText = findViewById<EditText>(R.id.pwdTextInputEditText)
        val pwd = pwdEditText.text.toString()
        val pwdPattern = Regex("^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[\$@\$!%*#?&.])[A-Za-z[0-9]\$@\$!%*#?&.]{8,16}\$")
        return if (pwdEditText.text.isEmpty()) {
            pwdEditText.error = "비밀번호를 입력해주세요."
            false
        } else if (!pwd.matches(pwdPattern)){
            pwdEditText.error = "영문, 숫자, 특수문자를 모두 포함하여 주세요."
            false
        } else {
            pwdEditText.error = null
            true
        }
    }
    fun isValidPwdCheck(pwd : String) : Boolean {
        val pwdCheckEditText = findViewById<EditText>(R.id.pwdCheckTextInputEditText)
        val pwdCheck = pwdCheckEditText.text.toString()
        val pwdCheckPattern = Regex("^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[\$@\$!%*#?&.])[A-Za-z[0-9]\$@\$!%*#?&.]{8,16}\$")
        return if (pwdCheck == pwd) {
            pwdCheckEditText.error = null
            true
        } else {
            pwdCheckEditText.error = "비밀번호가 다릅니다."
            false
        }
    }
}
