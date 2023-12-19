package com.example.introducemyself

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.core.widget.addTextChangedListener

class SignUpActivity : AppCompatActivity() {

    private val nameEditText by lazy { findViewById<EditText>(R.id.nameTextInputEditText) }
    private val ageEditText by lazy { findViewById<EditText>(R.id.ageTextInputEditText) }
    private val mbtiEditText by lazy { findViewById<EditText>(R.id.mbtiTextInputEditText) }
    private val idEditText by lazy { findViewById<EditText>(R.id.idTextInputEditText) }
    private val pwdEditText by lazy { findViewById<EditText>(R.id.pwdTextInputEditText) }
    private val pwdCheckEditText by lazy { findViewById<EditText>(R.id.pwdCheckTextInputEditText) }
    private val emailEditText by lazy { findViewById<EditText>(R.id.emailTextInputEditText) }
    private val emailTextInputLayout by lazy { findViewById<View>(R.id.emailTextInputLayout) }
    private val signUpBtn by lazy { findViewById<Button>(R.id.btn_signUp) }
    private val spinner by lazy { findViewById<Spinner>(R.id.spinner) }

    private val editTexts
        get() = listOf(
            nameEditText,
            ageEditText,
            mbtiEditText,
            idEditText,
            emailEditText,
            pwdEditText,
            pwdCheckEditText
        )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        initView()
    }

    private fun initView() {
        setServiceProvider()

        setTextChangedListener()

        setSignUpButton()
    }

    private fun setTextChangedListener() {
        editTexts.forEach { editText ->
            editText.addTextChangedListener {
                editText.setErrorMessage()
                setSignUpButtonEnabled()
            }
        }
    }

    private fun setSignUpButton() {
        signUpBtn.setOnClickListener {
            val inputId = when (spinner.selectedItemPosition) {
                1 -> idEditText.text.toString() + emailEditText.text.toString()
                2,3,4 -> idEditText.text.toString() + spinner.selectedItem.toString()
                else -> idEditText.text.toString()
            }
            val inputPwd = pwdEditText.text.toString()
            if (!inputId.matches(Regex("^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}\$"))) {
                Toast.makeText(this, getString(R.string.sign_up_id_error_pattern), Toast.LENGTH_SHORT).show()
            } else {
                idEditText.error = null
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
    }

    private fun setServiceProvider() {
        spinner.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            listOf(
                getString(R.string.sign_up_email_provider_select_menu),
                getString(R.string.sign_up_email_provider_direct),
                getString(R.string.sign_up_email_provider_gmail),
                getString(R.string.sign_up_email_provider_naver),
                getString(R.string.sign_up_email_provider_kakao)
            )
        )
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> signUpBtn.isEnabled = false
                    1 -> {
                        spinner.visibility = View.GONE
                        emailTextInputLayout.visibility = View.VISIBLE
                    }
                    else -> signUpBtn.isEnabled = true
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) = Unit
        }
    }

    private fun EditText.setErrorMessage() {
        when (this) {
            nameEditText -> nameEditText.error = isValidName()
            ageEditText -> ageEditText.error = isValidAge()
            mbtiEditText -> mbtiEditText.error = isValidMbti()
            idEditText -> idEditText.error = isValidId()
            pwdEditText -> pwdEditText.error = isValidPwd()
            pwdCheckEditText -> pwdCheckEditText.error = isValidPwdCheck()
            emailEditText -> emailEditText.error = isValidEmail()
        }
    }

    private fun setSignUpButtonEnabled() {
        signUpBtn.isEnabled = when (spinner.selectedItemPosition) {
            0 -> false
            1 -> {
                isValidName()?.isBlank() ?: true
                        && isValidAge()?.isBlank() ?: true
                        && isValidMbti()?.isBlank() ?: true
                        && isValidId()?.isBlank() ?: true
                        && isValidPwd()?.isBlank() ?: true
                        && isValidPwdCheck()?.isBlank() ?: true
                        && isValidEmail()?.isBlank() ?: true
            }
            2,3,4 -> {
                isValidName()?.isBlank() ?: true
                        && isValidAge()?.isBlank() ?: true
                        && isValidMbti()?.isBlank() ?: true
                        && isValidId()?.isBlank() ?: true
                        && isValidPwd()?.isBlank() ?: true
                        && isValidPwdCheck()?.isBlank() ?: true
            }
            else -> false
        }
    }

    private fun isValidName() : String? {
        val namePattern = Regex("^[a-zA-Z가-힣]*\$")
        return when {
            nameEditText.text.isBlank() -> getString(R.string.sign_up_name_error_blank)
            !nameEditText.text.toString().matches(namePattern) -> getString(R.string.sign_up_name_error_pattern)
            else -> null
        }
    }

    private fun isValidAge(): String? {
        val age = ageEditText.text.toString()
        return when {
            age.isBlank() -> getString(R.string.tv_homeAge)
            age.toInt() < 15 || age.toInt() > 100 -> getString(R.string.sign_up_age_error_limit)
            else -> null
        }
    }

    private fun isValidMbti() : String? {
        val mbti = mbtiEditText.text.toString()
        val mbtiPattern = Regex("[EI][NS][FT][JP]")
        return when {
            mbti.isBlank() -> getString(R.string.tv_homeMBTI)
            !mbti.matches(mbtiPattern) -> getString(R.string.sign_up_mbti_error_pattern)
            else -> null
        }
    }

    private fun isValidId() : String? {
        val id = idEditText.text.toString()
        val idPattern = Regex("^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\$")

        return when {
            id.isBlank() -> getString(R.string.tv_idHint)
            !id.matches(idPattern) -> getString(R.string.sign_up_id_error_pattern2)
            else -> null
        }
    }

    private fun isValidEmail() : String? {
        val email = when(spinner.selectedItemPosition) {
            0 -> ""
            1 -> emailEditText.text.toString()
            2 -> getString(R.string.sign_up_email_provider_gmail)
            3 -> getString(R.string.sign_up_email_provider_naver)
            4 -> getString(R.string.sign_up_email_provider_kakao)
            else -> ""
        }
        val emailPattern = Regex("^@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}\$")

        return when {
            email.isBlank() -> getString(R.string.tv_idHint)
            !email.matches(emailPattern) -> getString(R.string.sign_up_email_error_pattern)
            else -> null
        }
    }

    private fun isValidPwd() : String? {
        val pwd = pwdEditText.text.toString()
        val pwdPattern = Regex("^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[\$@\$!%*#?&.])[A-Za-z[0-9]\$@\$!%*#?&.]{8,16}\$")
        return when {
            pwd.isBlank() -> getString(R.string.tv_pwdHint)
            !pwd.matches(pwdPattern) -> getString(R.string.sign_up_pwd_error_pattern)
            else -> null
        }
    }
    private fun isValidPwdCheck() : String? {
        val pwdCheck = pwdCheckEditText.text.toString()
        return if (pwdCheck != pwdEditText.text.toString()) {
            getString(R.string.sign_up_pwd_check_error)
        } else {
            null
        }
    }
}