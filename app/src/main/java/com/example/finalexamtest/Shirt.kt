package com.example.finalexamtest

import com.google.gson.annotations.SerializedName

data class Shirt(
    @SerializedName("id") val id: Int = 0,
    @SerializedName("customer_name") val name: String = "",
    @SerializedName("shirt_type") val type: String = "",
    @SerializedName("shirt_size") val size: String = "",
    @SerializedName("total_price") val price: Int = 0
)