package com.example.harrisonwjy.charitree.model.request

import com.example.harrisonwjy.charitree.model.DonationItems

class CreateDonationRequest {
    companion object Factory {
        fun create(): CreateDonationRequest = CreateDonationRequest()
    }

    var address_id: Int? = null
    var pickup_date: String? = null
    var pickup_time: String? = null
    var items: DonationItems? = null
}