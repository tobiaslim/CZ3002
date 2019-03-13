package com.example.harrisonwjy.charitree.model

class User {

    companion object Factory {
        fun create(): User = User()
    }

    var status: Int = 0
    var user_token: String =""
    var id: Int? = 0
    var email: String? = null
    var first_name: String? = null
    var last_name: String? = null
}
