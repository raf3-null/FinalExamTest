package com.example.finalexamtest

import retrofit2.Response
import retrofit2.http.*

interface ShirtAPI {
    @GET("api/Shirt")
    suspend fun retrieveFood(): List<Shirt>

    @FormUrlEncoded
    @POST("api/Shirt")
    suspend fun insertFood(
        @Field("customer_name") name: String,
        @Field("shirt_type") type: String,
        @Field("shirt_size") size: String,
        @Field("total_price") price: Int
    ): Shirt

    @DELETE("api/Shirt/{id}")
    suspend fun deleteFood(
        @Path("id") id: Int
    ): Response<Void>
}