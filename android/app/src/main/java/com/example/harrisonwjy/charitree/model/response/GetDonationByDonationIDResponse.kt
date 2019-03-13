package com.example.harrisonwjy.charitree.model.response

import com.example.harrisonwjy.charitree.model.Donation
import com.example.harrisonwjy.charitree.model.Errors

class GetDonationByDonationIDResponse {

    companion object {
        fun create(): GetDonationByDonationIDResponse = GetDonationByDonationIDResponse()
    }

    var status: Int? = 0
    var messages: String? = null
    var errors: Errors? = null
    var donation: Donation? = null
}