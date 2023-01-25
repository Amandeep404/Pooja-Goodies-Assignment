package com.example.assignment1.utils

class Constants {
    companion object{

        const val BASE_URL = "https://www.googleapis.com/youtube/v3/"
        const val API_KEY = "AIzaSyDsCa2oKLw_0rGMf8T5kbicHbbdxbioaCc"

        const val LIST_OF_REQUIRED_CHANNEL_ID = "UC_x5XG1OV2P6uZZ5FSM9Ttw, UCRUAdVm9ZOF4JheOd8qIQHA "

        //End point to get list of channels
        const val LIST_OF_CHANNELS = "channels"

        //Part Properties
        const val SNIPPET = "snippet"
        const val DETAILS = "contentDetails"
        const val STATISTICS = "statistics"

        const val HOME_API_CALL = "https://youtube.googleapis.com/youtube/v3/channels?part=snippet&id=UC_x5XG1OV2P6uZZ5FSM9Ttw%2C%20UCRUAdVm9ZOF4JheOd8qIQHA%20&key=AIzaSyDsCa2oKLw_0rGMf8T5kbicHbbdxbioaCc"
    }
}