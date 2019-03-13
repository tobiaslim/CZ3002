package com.example.harrisonwjy.charitree.model.request

class RegisterCMRequest{
    companion object Factory {
        fun create(): RegisterCMRequest = RegisterCMRequest()
    }

    var organization_name: String? = ""
    var UEN: String? = ""


}