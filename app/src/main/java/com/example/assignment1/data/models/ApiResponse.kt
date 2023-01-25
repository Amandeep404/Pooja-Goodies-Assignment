package com.example.assignment1.data.models

data class ApiResponse(
    val items: List<Item>,
    val pageInfo: PageInfo
)