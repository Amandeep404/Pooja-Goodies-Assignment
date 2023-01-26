package com.example.assignment1.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment1.data.models.ApiResponse
import com.example.assignment1.data.searchModel.SearchResponse
import com.example.assignment1.repo.FetchChannelListRepository
import com.example.assignment1.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject
import kotlin.math.max

@HiltViewModel
class ChannelListViewModel @Inject constructor(
    private val repository: FetchChannelListRepository
) : ViewModel(){

    private val _channelList : MutableLiveData<Resource<ApiResponse>> = MutableLiveData()
    var channelList : LiveData<Resource<ApiResponse>> = _channelList

    private val _popularVideoList : MutableLiveData<Resource<SearchResponse>> = MutableLiveData()
    var popularVideoList : LiveData<Resource<SearchResponse>> = _popularVideoList

    init {
        fetchChannelList()
    }

    private fun fetchChannelList() = viewModelScope.launch {
        _channelList.postValue(Resource.Loading())
        val response = repository.getChannelList()
        _channelList.postValue(handleApiResponse(response))
    }

    fun fetchPopularVideos(channelId: String, maxResult :Int = 15) = viewModelScope.launch {
        _popularVideoList.postValue(Resource.Loading())
        val response = repository.getPopularVideos(channelId, maxResult)
        _popularVideoList.postValue(handleSearchResponse(response))
    }

    private fun handleSearchResponse(response: Response<SearchResponse>): Resource<SearchResponse>{
        if (response.isSuccessful){
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleApiResponse(response: Response<ApiResponse>): Resource<ApiResponse>{
        if (response.isSuccessful){
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }
}