package com.example.composemoviedemo.discount.model

import com.google.gson.annotations.SerializedName

class DiscountResponse : ArrayList<DiscountResponse.Item>() {
    data class Item(
        @SerializedName("albumId")
        val albumId: Int,
        @SerializedName("id")
        val id: Int,
        @SerializedName("thumbnailUrl")
        val thumbnailUrl: String,
        @SerializedName("title")
        val title: String,
        @SerializedName("url")
        val url: String
    )
}