package com.example.harrisonwjy.charitree.model.response

import com.example.harrisonwjy.charitree.model.Errors

class UserRegisterResponse {
    companion object Factory {
        fun create(): LoginResponse = LoginResponse()
    }

    var status: Int? = 0
    var errors: Errors? = null
}
