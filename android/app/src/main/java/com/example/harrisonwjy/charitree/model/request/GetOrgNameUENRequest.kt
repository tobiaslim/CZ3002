package com.example.harrisonwjy.charitree.model.request

class GetOrgNameUENRequest{

    companion object Factory {
        fun create(): GetOrgNameUENRequest = GetOrgNameUENRequest()
    }
    var uen : String? = null
}