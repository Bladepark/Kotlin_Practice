package com.example.introducemyself

object MemberInfo {
    var memberInfo = arrayListOf<MemberData>()

    fun updateMember(emailSearch: String?, updatedMember: MemberData) { // 회원 정보 수정
        memberInfo.find { it.id == emailSearch }?.apply {
            name = updatedMember.name
            id = updatedMember.id
            pwd = updatedMember.pwd
            age = updatedMember.age
            mbti = updatedMember.mbti
        }
    }
}