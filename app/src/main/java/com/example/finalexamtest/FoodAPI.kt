package com.example.finalexamtest

import retrofit2.Response
import retrofit2.http.*

interface FoodAPI {
    @GET("api/food")
    suspend fun retrieveFood(): List<Food>

    @FormUrlEncoded
    @POST("api/food")
    suspend fun insertFood(
        @Field("id") id: String,
        @Field("name") name: String,
        @Field("price") price: Int,
        @Field("amount") amount: Int
    ): Food
}