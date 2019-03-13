package com.example.harrisonwjy.charitree.model.response

import com.example.harrisonwjy.charitree.model.Errors

class GetSessionsResponse {
    companion object {
        fun create(): GetSessionsResponse = GetSessionsResponse()
    }

    var status: Int? = 0
    var message: String? = null
    var errors: Errors? = null
}