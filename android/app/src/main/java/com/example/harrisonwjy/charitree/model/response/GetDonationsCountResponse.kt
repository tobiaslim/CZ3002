package com.example.harrisonwjy.charitree.model.response

import com.example.harrisonwjy.charitree.model.Errors

class GetDonationsCountResponse {
    companion object {
        fun create(): GetDonationsCountResponse = GetDonationsCountResponse()
    }

    var status: Int? = 0
    var count: Int? = 0
    var countBy: String? = null
    var errors: Errors? = null
}