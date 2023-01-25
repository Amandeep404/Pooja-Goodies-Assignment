package com.example.assignment1.repo

import com.example.assignment1.api.ApiInterface
import com.example.assignment1.data.models.ApiResponse
import retrofit2.Response
import javax.inject.Inject

class FetchChannelListRepository @Inject constructor(
    private val api :  ApiInterface
) {
    suspend fun getChannelList() : Response<ApiResponse> {
        return api.fetchRequiredChannels()
    }
}