package com.example.assignment1.data.models

data class Snippet(
    val channelId: String,
    val title: String,
    val country: String,
    val description: String,
    val channelTitle : String,
    val customUrl : String,
    val publishedAt: String,
    val thumbnails: Thumbnails

)