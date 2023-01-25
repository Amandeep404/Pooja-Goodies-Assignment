package com.example.assignment1.api

import com.example.assignment1.data.models.ApiResponse
import com.example.assignment1.utils.Constants.Companion.API_KEY
import com.example.assignment1.utils.Constants.Companion.DETAILS
import com.example.assignment1.utils.Constants.Companion.LIST_OF_CHANNELS
import com.example.assignment1.utils.Constants.Companion.LIST_OF_REQUIRED_CHANNEL_ID
import com.example.assignment1.utils.Constants.Companion.SNIPPET
import com.example.assignment1.utils.Constants.Companion.STATISTICS
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET(LIST_OF_CHANNELS)
    suspend fun fetchRequiredChannels(
        @Query("part") part: String = "$SNIPPET,$DETAILS,$STATISTICS",
        @Query("id") channelIDs : String = "UC_x5XG1OV2P6uZZ5FSM9Ttw",
        @Query("key") apiKey : String = API_KEY
    ): Response<ApiResponse>
}