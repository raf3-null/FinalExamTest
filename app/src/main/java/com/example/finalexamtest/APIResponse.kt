package com.example.finalexamtest

data class ApiResponse(
    val error: Boolean?,
    val message: String?,
    val insertId: Int? = null
)