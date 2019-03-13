package com.example.harrisonwjy.charitree.model

import java.io.Serializable

class Campaign : Serializable {
    companion object {
        fun create(): Campaign = Campaign()
    }

    var id: Int? = 0
    var name: String? = null
    var start_date : String? = null
    var end_date: String? = null
    var start_time: Int? = 0
    var end_time: Int? = 0
    var collection_point: String? = ""
    var description: String? = ""
    var postal_code: String? = ""
    var cid: Int? = 0
    var total_donations: Int? = 0
    var pending_donations: Int? = 0
    var inprogress_donations: Int? = 0
    var campaign_manager: CampaignManager? = null
    var accepted_items: ArrayList<AcceptedItem>? = null
    var days_left: Int? = 0
    var weather_forecasts : ArrayList<WeatherForecast> = ArrayList()

}