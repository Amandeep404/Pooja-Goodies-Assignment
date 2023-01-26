package com.example.assignment1.data.models

data class ApiResponse(
    val id: String,
    val items: List<Item>,
    val pageInfo: PageInfo
)