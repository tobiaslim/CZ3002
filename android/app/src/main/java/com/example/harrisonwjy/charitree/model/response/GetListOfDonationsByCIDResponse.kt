package com.example.harrisonwjy.charitree.model.response

import com.example.harrisonwjy.charitree.model.Donation
import com.example.harrisonwjy.charitree.model.Errors

class GetListOfDonationsByCIDResponse {
    companion object {
        fun create(): GetListOfDonationsByCIDResponse = GetListOfDonationsByCIDResponse()
    }

    var status: Int? = 0
    var message: String? = null
    var donations: ArrayList<Donation>? = ArrayList()
    var errors: Errors? = null
}