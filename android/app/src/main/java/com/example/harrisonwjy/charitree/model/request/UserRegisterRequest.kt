package com.example.harrisonwjy.charitree.model.request

class UserRegisterRequest {

    companion object Factory {
        fun create(): UserRegisterRequest = UserRegisterRequest()
    }

    var email: String = ""
    var first_name: String = ""
    var last_name: String = ""
    var password: String = ""
}