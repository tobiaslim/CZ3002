package com.example.harrisonwjy.charitree.model.response

import com.example.harrisonwjy.charitree.model.Errors

// for API POST/user/campaignmanagers

class CMRegisterResponse {
    companion object Factory {
        fun create(): CMRegisterResponse = CMRegisterResponse()
    }

    var status: Int? = 0
    var errors: Errors? = null
}