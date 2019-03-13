package com.example.harrisonwjy.charitree.model.response

import com.example.harrisonwjy.charitree.model.Errors

class CreateCampaignResponse {
    companion object {
        fun create(): CreateCampaignResponse = CreateCampaignResponse()
    }

    var status: Int? = 0
    var message: String? = null
    var errors: Errors? = null
}