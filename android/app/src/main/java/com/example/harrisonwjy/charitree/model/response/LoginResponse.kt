package com.example.harrisonwjy.charitree.model.response

import com.example.harrisonwjy.charitree.model.Errors

class LoginResponse{
    companion object Factory {
        fun create(): LoginResponse = LoginResponse()
    }

    var status: Int? = 0
    var user_token: String? = null
    var errors: Errors? = null
}

