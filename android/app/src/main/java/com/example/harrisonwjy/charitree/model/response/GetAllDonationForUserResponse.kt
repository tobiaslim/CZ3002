package com.example.harrisonwjy.charitree.model.response

import com.example.harrisonwjy.charitree.model.Donation
import com.example.harrisonwjy.charitree.model.Errors

//Get all donations of a user by the current session
class GetAllDonationForUserResponse {
    companion object {
        fun create(): GetAllDonationForUserResponse = GetAllDonationForUserResponse()
    }

    var status :Int? = 0
    var message: String? = null
    var donations: ArrayList<Donation>? = ArrayList()
    var errors: Errors? = null
}