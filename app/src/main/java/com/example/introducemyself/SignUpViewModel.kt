package com.example.introducemyself

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


enum class SignUpErrorMessage(
    @StringRes val message: Int
) {
    NAME_BLANK(R.string.sign_up_name_error_blank),
    NAME_PATTERN(R.string.sign_up_name_error_pattern),

    AGE_BLANK(R.string.tv_homeAge),
    AGE_PATTERN(R.string.sign_up_age_error_limit),

    MBTI_BLANK(R.string.tv_homeMBTI),
    MBTI_PATTERN(R.string.sign_up_mbti_error_pattern),

    ID_BLANK(R.string.tv_idHint),
    ID_PATTERN(R.string.sign_up_id_error_pattern2),

    EMAIL_BLANK(R.string.tv_idHint),
    EMAIL_PATTERN(R.string.sign_up_email_error_pattern),

    PWD_BLANK(R.string.tv_pwdHint),
    PWD_PATTERN(R.string.sign_up_pwd_error_pattern),

    PWDCHECK_PATTERN(R.string.sign_up_pwd_check_error)
    ;
}

class SignUpViewModel : ViewModel() {
    private val _nameErrMsg = MutableLiveData<String?>()
    val nameErrMsg: LiveData<String?>
        get() = _nameErrMsg

    private val _ageErrMsg = MutableLiveData<String?>()
    val ageErrMsg: LiveData<String?>
        get() = _ageErrMsg

    private val _mbtiErrMsg = MutableLiveData<String?>()
    val mbtiErrMsg: LiveData<String?>
        get() = _mbtiErrMsg

    private val _idErrMsg = MutableLiveData<String?>()
    val idErrMsg: LiveData<String?>
        get() = _idErrMsg

    private val _pwdErrMsg = MutableLiveData<String?>()
    val pwdErrMsg: LiveData<String?>
        get() = _pwdErrMsg

    private val _pwdCheckErrMsg = MutableLiveData<String?>()
    val pwdCheckErrMsg: LiveData<String?>
        get() = _pwdCheckErrMsg

    private val _emailErrMsg = MutableLiveData<String?>()
    val emailErrMsg: LiveData<String?>
        get() = _emailErrMsg

    private val _isSignUpBtnEnabled = MutableLiveData<Boolean>()
    val isSignUpBtnEnabled: LiveData<Boolean>
        get() = _isSignUpBtnEnabled

//    private val errorMessages
//        get() = listOf(
//            _nameErrMsg,
//            _ageErrMsg,
//            _mbtiErrMsg,
//            _idErrMsg,
//            _pwdErrMsg,
//            _pwdCheckErrMsg,
//            _emailErrMsg
//        )

    fun getMessageValidName(name: String) {
        val namePattern = Regex("^[a-zA-Z가-힣]*\$")
        _nameErrMsg.value = when {
            name.isBlank() -> SignUpErrorMessage.NAME_BLANK.message.toString()
            name.matches(namePattern).not() -> SignUpErrorMessage.NAME_PATTERN.message.toString()
            else -> null
        }
    }

    private fun getMessageValidAge(age: String) {
        _ageErrMsg.value = when {
            age.isBlank() -> SignUpErrorMessage.AGE_BLANK.message.toString()
            age.toInt() < 15 || age.toInt() > 100 -> SignUpErrorMessage.AGE_PATTERN.message.toString()
            else -> null
        }
    }

    private fun getMessageValidMbti(mbti: String) {
        val mbtiPattern = Regex("[EI][NS][FT][JP]")
        _mbtiErrMsg.value = when {
            mbti.isBlank() -> SignUpErrorMessage.MBTI_BLANK.message.toString()
            mbti.matches(mbtiPattern).not() -> SignUpErrorMessage.MBTI_PATTERN.message.toString()
            else -> null
        }
    }

    private fun getMessageValidId(id: String) {
        val idPattern = Regex("^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\$")
        _idErrMsg.value = when {
            id.isBlank() -> SignUpErrorMessage.ID_BLANK.message.toString()
            id.matches(idPattern).not() -> SignUpErrorMessage.ID_PATTERN.message.toString()
            else -> null
        }
    }

    private fun getMessageValidEmail(email: String) {
//        val spEmail = when (spinner.selectedItemPosition) {
//            0 -> ""
//            1 -> emailEditText.text.toString()
//            2 -> SignUpErrorMessage.EMAIL_BLANK.message.toString()  // 수정
//            3 -> SignUpErrorMessage.EMAIL_BLANK.message.toString()  // 수정
//            4 -> SignUpErrorMessage.EMAIL_BLANK.message.toString()  // 수정
//            else -> ""
//        }
        val emailPattern = Regex("^@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}\$")

        _emailErrMsg.value = when {
            email.isBlank() -> SignUpErrorMessage.EMAIL_BLANK.message.toString()
            email.matches(emailPattern).not() -> SignUpErrorMessage.EMAIL_PATTERN.message.toString()
            else -> null
        }
    }

    private fun getMessageValidPwd(pwd: String) {
        val pwdPattern =
            Regex("^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[\$@\$!%*#?&.])[A-Za-z[0-9]\$@\$!%*#?&.]{8,16}\$")
        _pwdErrMsg.value = when {
            pwd.isBlank() -> SignUpErrorMessage.PWD_BLANK.message.toString()
            pwd.matches(pwdPattern).not() -> SignUpErrorMessage.PWD_PATTERN.message.toString()
            else -> null
        }
    }

    private fun getMessageValidPwdCheck(text: String) {
        val pwdCheck = pwdCheckEditText.text.toString()
        _pwdCheckErrMsg.value = if (pwdCheck != pwdEditText.text.toString()) {
            SignUpErrorMessage.PWDCHECK_PATTERN.message.toString()
        } else {
            null
        }
    }
}