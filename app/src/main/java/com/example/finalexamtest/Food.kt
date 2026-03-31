package com.example.finalexamtest

import com.google.gson.annotations.SerializedName

data class Food(
    @SerializedName("id") val id: String = "",
    @SerializedName("name") val name: String = "",
    @SerializedName("price") val price: Int = 0,
    @SerializedName("amount") val amount: Int = 0
)