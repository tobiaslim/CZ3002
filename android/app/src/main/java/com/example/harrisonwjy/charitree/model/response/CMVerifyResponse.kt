package com.example.harrisonwjy.charitree.model.response

import com.example.harrisonwjy.charitree.model.CampaignManager
import com.example.harrisonwjy.charitree.model.Errors

// GET http://{{baseurl}}/users/campaignmanagers HTTP/1.1

class CMVerifyResponse {
    companion object Factory {
        fun create(): CMVerifyResponse = CMVerifyResponse()
    }
    var status: Int? = 0
    var campaign_manager: CampaignManager? = null
    var errors: Errors? = null
}

