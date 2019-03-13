package com.example.harrisonwjy.charitree.model.response

import com.example.harrisonwjy.charitree.model.Errors

class ChangeStatusDonationResponse {
    companion object {
        fun create(): ChangeStatusDonationResponse = ChangeStatusDonationResponse()
    }

    var status: Int? = 0
    var errors: Errors? = null
}