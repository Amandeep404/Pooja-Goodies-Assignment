package com.example.assignment1.api

import com.example.assignment1.data.models.ApiResponse
import com.example.assignment1.data.searchModel.SearchResponse
import com.example.assignment1.utils.Constants.Companion.API_KEY
import com.example.assignment1.utils.Constants.Companion.CHANNEL_ID
import com.example.assignment1.utils.Constants.Companion.DETAILS
import com.example.assignment1.utils.Constants.Companion.LIST_OF_CHANNELS
import com.example.assignment1.utils.Constants.Companion.LIST_OF_REQUIRED_CHANNEL_ID
import com.example.assignment1.utils.Constants.Companion.MAX_RESULTS
import com.example.assignment1.utils.Constants.Companion.ORDER
import com.example.assignment1.utils.Constants.Companion.SEARCH
import com.example.assignment1.utils.Constants.Companion.SNIPPET
import com.example.assignment1.utils.Constants.Companion.STATISTICS
import com.example.assignment1.utils.Constants.Companion.VIEW_COUNT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET(LIST_OF_CHANNELS)
    suspend fun fetchRequiredChannels(
        @Query("part") part: String = SNIPPET,
        @Query("id") channelIDs : String = LIST_OF_REQUIRED_CHANNEL_ID,
        @Query("key") apiKey : String = API_KEY
    ): Response<ApiResponse>

    @GET(SEARCH)
    suspend fun fetchPopularVideos(
        @Query("part") part : String = SNIPPET,
        @Query(CHANNEL_ID) channelId : String? ,
        @Query(MAX_RESULTS) maxResults: Int = 15,
        @Query(ORDER) order: String = VIEW_COUNT,
        @Query("key") key : String = API_KEY
    ): Response<SearchResponse>

}