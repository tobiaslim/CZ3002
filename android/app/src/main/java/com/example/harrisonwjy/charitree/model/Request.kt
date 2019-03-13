package com.example.harrisonwjy.charitree.model

class Request{
    companion object Factory {
        fun create(): Request = Request()
    }

    var email: String = ""
    var first_name: String = ""
    var last_name: String =""
    var password: String = ""
    var user_token: String = ""

}