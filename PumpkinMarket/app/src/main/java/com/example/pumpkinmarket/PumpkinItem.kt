package com.example.pumpkinmarket

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PumpkinItem(
    val itemImage: Int,
    val itemTitle: String,
    val itemDescription: String,
    val itemSeller: String,
    val itemAddress: String,
    val itemPrice: String,
    var itemChat: String,
    var itemLike: Int,
    var isLiked: Boolean
) : Parcelable