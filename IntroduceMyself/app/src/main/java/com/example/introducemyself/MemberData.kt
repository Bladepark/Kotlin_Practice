package com.example.introducemyself

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MemberData(
    var id: String,
    var pwd: String,
    var name: String,
    var age: String,
    var mbti: String
) : Parcelable