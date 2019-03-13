package com.example.harrisonwjy.charitree.model.request

import com.example.harrisonwjy.charitree.model.Request

class LoginRequest {
    companion object Factory {
        fun create(): LoginRequest = LoginRequest()
    }

    var email: String = ""
    var password: String = ""
}