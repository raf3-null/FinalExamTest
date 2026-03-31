package com.example.finalexamtest

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object FoodClient {
    private const val BASE_URL = "http://10.0.2.2:3000/"

    val foodAPI: FoodAPI by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FoodAPI::class.java)
    }
}
