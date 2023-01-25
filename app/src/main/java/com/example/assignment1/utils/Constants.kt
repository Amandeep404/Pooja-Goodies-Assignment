package com.example.assignment1.utils

class Constants {
    companion object{

        const val BASE_URL = "https://www.googleapis.com/youtube/v3/"
        const val API_KEY = "AIzaSyDsCa2oKLw_0rGMf8T5kbicHbbdxbioaCc"

        const val LIST_OF_REQUIRED_CHANNEL_ID = "UCRUAdVm9ZOF4JheOd8qIQHA, UCDe0DwkMVFfSIoiYdQUPQmQ, UCJeQx6mAyNdPUc9sJA866Xw, UCSzOZ97LOpU-_AVlGfmD4rQ, UCHq7ZxlzRRXimaBmk5QAxSQ, UCUUIz69kK3Ib5bD4hWLKAwA, UC8Igqo3g1U40n66BLb-xHuQ, UCCR5eciEJIMvX2o1tiYOUKQ, UCfwa_zKl8-zC9rQDWIEixgg, UC5tCh2Nz6dndKnBPxH3PFbg, UC04m8d9t8UeWZ5DuvQVnqiw, UCuUiGz3gMFkgvYU4-hGRJdw, UCOizxR3GwY7dmehMCAdvv9g, UCyIkg79GpPVF77qYKoAINtw, UCDqkux3AH7P9hRjmunoUeAQ, UC7ZivIYRB0fMSGh-THcTYbw, UCaayLD9i5x4MmIoVZxXSv_g, UCHKGDg0GJKBsA9mFraDOLHA"

        //End point to get list of channels
        const val LIST_OF_CHANNELS = "channels"

        //Part Properties
        const val SNIPPET = "snippet"
        const val DETAILS = "contentDetails"
        const val STATISTICS = "statistics"

        const val HOME_API_CALL = "https://youtube.googleapis.com/youtube/v3/channels?part=snippet&id=UC_x5XG1OV2P6uZZ5FSM9Ttw%2C%20UCRUAdVm9ZOF4JheOd8qIQHA%20&key=AIzaSyDsCa2oKLw_0rGMf8T5kbicHbbdxbioaCc"
    }
}