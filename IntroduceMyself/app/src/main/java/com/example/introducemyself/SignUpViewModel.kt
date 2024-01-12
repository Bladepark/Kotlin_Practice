package com.example.introducemyself

import android.util.Log
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


    fun getMessageValidName(name: String) {
        val namePattern = Regex("^[a-zA-Z가-힣]*\$")
        _nameErrMsg.value = when {
            // TODO 현재 스트링 받아오는 곳이 문제가 있음.
            name.isBlank() -> SignUpErrorMessage.NAME_BLANK.message.toString()
            name.matches(namePattern).not() -> SignUpErrorMessage.NAME_PATTERN.message.toString()
            else -> null
        }
        Log.d("EDITTEXTCHECK", _nameErrMsg.value.toString())
    }

    fun getMessageValidAge(age: String) {
        _ageErrMsg.value = when {
            age.isBlank() -> SignUpErrorMessage.AGE_BLANK.message.toString()
            age.toInt() < 15 || age.toInt() > 100 -> SignUpErrorMessage.AGE_PATTERN.message.toString()
            else -> null
        }
        Log.d("EDITTEXTCHECK", _ageErrMsg.value.toString())
    }

    fun getMessageValidMbti(mbti: String) {
        val mbtiPattern = Regex("[EI][NS][FT][JP]")
        _mbtiErrMsg.value = when {
            mbti.isBlank() -> SignUpErrorMessage.MBTI_BLANK.message.toString()
            mbti.matches(mbtiPattern).not() -> SignUpErrorMessage.MBTI_PATTERN.message.toString()
            else -> null
        }
        Log.d("EDITTEXTCHECK", _mbtiErrMsg.value.toString())
    }

    fun getMessageValidId(id: String) {
        val idPattern = Regex("^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\$")
        _idErrMsg.value = when {
            id.isBlank() -> SignUpErrorMessage.ID_BLANK.message.toString()
            id.matches(idPattern).not() -> SignUpErrorMessage.ID_PATTERN.message.toString()
            else -> null
        }
        Log.d("EDITTEXTCHECK", _idErrMsg.value.toString())
    }

    fun getMessageValidEmail(email: String) {
        val emailPattern = Regex("^@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}\$")

        _emailErrMsg.value = when {
            email.isBlank() -> SignUpErrorMessage.EMAIL_BLANK.message.toString()
            email.matches(emailPattern).not() -> SignUpErrorMessage.EMAIL_PATTERN.message.toString()
            else -> null
        }
        Log.d("EDITTEXTCHECK", _emailErrMsg.value.toString())
    }

    fun getMessageValidPwd(pwd: String) {
        val pwdPattern =
            Regex("^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[\$@\$!%*#?&.])[A-Za-z[0-9]\$@\$!%*#?&.]{8,16}\$")
        _pwdErrMsg.value = when {
            pwd.isBlank() -> SignUpErrorMessage.PWD_BLANK.message.toString()
            pwd.matches(pwdPattern).not() -> SignUpErrorMessage.PWD_PATTERN.message.toString()
            else -> null
        }
        Log.d("EDITTEXTCHECK", _pwdErrMsg.value.toString())
    }

    fun getMessageValidPwdCheck(pwd: String, pwdCheck: String) {
        _pwdCheckErrMsg.value = if (pwdCheck != pwd) {
            SignUpErrorMessage.PWDCHECK_PATTERN.message.toString()
        } else {
            null
        }
        Log.d("EDITTEXTCHECK", _pwdCheckErrMsg.value.toString())
    }

    fun setSignUpButtonEnabled(spinnerPosition: Int) {
        _isSignUpBtnEnabled.value = when (spinnerPosition) {
            1 -> {
                nameErrMsg.value == null
                        && ageErrMsg.value == null
                        && mbtiErrMsg.value == null
                        && idErrMsg.value == null
                        && emailErrMsg.value == null
                        && pwdErrMsg.value == null
                        && pwdCheckErrMsg.value == null
            }

            2, 3, 4 -> {
                nameErrMsg.value == null
                        && ageErrMsg.value == null
                        && mbtiErrMsg.value == null
                        && idErrMsg.value == null
                        && pwdErrMsg.value == null
                        && pwdCheckErrMsg.value == null
            }

            else -> false
        }
        Log.d("_isSignUpBtnEnabled", _isSignUpBtnEnabled.value.toString())
        Log.d("spinnerPosition", spinnerPosition.toString())
    }

    fun onSignUpButtonClick(
        entryType: SignUpEntryType,
        inputId: String,
        inputPwd: String,
        inputName: String,
        inputAge: String,
        inputMbti: String,
        memberId: String?
    ) {
        if (!inputId.matches(Regex("^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}\$"))) {
            _idErrMsg.value = SignUpErrorMessage.ID_PATTERN.message.toString()
        } else {
            if (entryType == SignUpEntryType.UPDATE) {
                val updatedMember = MemberData(
                    id = inputId,
                    pwd  = inputPwd,
                    name = inputName,
                    age = inputAge,
                    mbti = inputMbti
                )
                MemberInfo.updateMember(memberId, updatedMember)
            } else {
                val newMemberData = MemberData(
                    id = inputId,
                    pwd  = inputPwd,
                    name = inputName,
                    age = inputAge,
                    mbti = inputMbti
                )
                MemberInfo.memberInfo.add(newMemberData)
            }
        }
    }
}