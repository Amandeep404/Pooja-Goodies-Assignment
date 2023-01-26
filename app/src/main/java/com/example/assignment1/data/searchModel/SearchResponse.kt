package com.example.assignment1.data.searchModel

data class SearchResponse(
    val etag: String,
    val items: List<SearchItem>,
    val kind: String,
    val nextPageToken: String,
    val pageInfo: PageInfo,
    val regionCode: String
)