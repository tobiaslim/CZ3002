package com.example.harrisonwjy.charitree.model

import java.io.Serializable

class WeatherForecast : Serializable {
    companion object Factory {
        fun create(): WeatherForecast = WeatherForecast()
    }

    var date: String? = null
    var forecast: String? = null
}