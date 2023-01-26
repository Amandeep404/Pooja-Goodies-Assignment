package com.example.assignment1.repo

import com.example.assignment1.api.ApiInterface
import com.example.assignment1.data.models.ApiResponse
import com.example.assignment1.data.searchModel.SearchResponse
import retrofit2.Response
import javax.inject.Inject

class FetchChannelListRepository @Inject constructor(
    private val api :  ApiInterface
) {
    suspend fun getChannelList() : Response<ApiResponse> {
        return api.fetchRequiredChannels()
    }

    suspend fun getPopularVideos(channelId: String, maxResult: Int = 15) : Response<SearchResponse>{
        return api.fetchPopularVideos(channelId = channelId, maxResults = maxResult)
    }
}