package com.example.harrisonwjy.charitree.model.response

import com.example.harrisonwjy.charitree.model.Errors

class CreateDonationResponse {

    companion object Factory {
        fun create(): CreateDonationResponse = CreateDonationResponse()
    }
    var status: Int? = null
    var message: String? = null
    var errors: Errors? = null
}