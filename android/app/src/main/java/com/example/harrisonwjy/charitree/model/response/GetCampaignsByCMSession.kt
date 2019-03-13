package com.example.harrisonwjy.charitree.model.response

import com.example.harrisonwjy.charitree.model.Campaign
import com.example.harrisonwjy.charitree.model.Errors

// Get all Campaigns By Current Campaign Manager Session
class GetCampaignsByCMSession{

    companion object Factory {
        fun create(): GetCampaignsByCMSession = GetCampaignsByCMSession()
    }

    var status: Int? = 0
    var message: String? = ""
    var errors : Errors? = null
    var campaigns: ArrayList<Campaign>? = ArrayList()
}