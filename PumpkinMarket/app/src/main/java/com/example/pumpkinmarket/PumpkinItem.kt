package com.example.pumpkinmarket

import android.os.Parcel
import android.os.Parcelable

data class PumpkinItem(
    val itemImage: Int,
    val itemTitle: String?,
    val itemDescription: String?,
    val itemSeller: String?,
    val itemAddress: String?,
    val itemPrice: String?,
    var itemChat: String?,
    var itemLike: Int,
    var isLiked: Boolean
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readBoolean()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(itemImage)
        parcel.writeString(itemTitle)
        parcel.writeString(itemDescription)
        parcel.writeString(itemSeller)
        parcel.writeString(itemAddress)
        parcel.writeString(itemPrice)
        parcel.writeString(itemChat)
        parcel.writeInt(itemLike)
        parcel.writeBoolean(isLiked)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PumpkinItem> {
        override fun createFromParcel(parcel: Parcel): PumpkinItem {
            return PumpkinItem(parcel)
        }

        override fun newArray(size: Int): Array<PumpkinItem?> {
            return arrayOfNulls(size)
        }
    }
}
