package com.example.introducemyself

import android.os.Parcel
import android.os.Parcelable

data class MemberData(
    var id: String?,
    var pwd: String?,
    var name: String?,
    var age: String?,
    var mbti: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(pwd)
        parcel.writeString(name)
        parcel.writeString(age)
        parcel.writeString(mbti)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MemberData> {
        override fun createFromParcel(parcel: Parcel): MemberData {
            return MemberData(parcel)
        }

        override fun newArray(size: Int): Array<MemberData?> {
            return arrayOfNulls(size)
        }
    }
}
