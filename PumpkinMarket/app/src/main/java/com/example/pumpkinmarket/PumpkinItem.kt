package com.example.pumpkinmarket

data class PumpkinItem(
    val itemImage: Int,
    val itemTitle: String,
    val itemDescription: String,
    val itemSeller: String,
    val itemAddress: String,
    val itemPrice: String,
    var itemChat: String,
    var itemLike: String
)
