package com.example.harrisonwjy.charitree.model.response

import com.example.harrisonwjy.charitree.model.Errors
import com.example.harrisonwjy.charitree.model.request.GetOrgNameUENRequest

class GetOrgNameUENResponse{

    companion object Factory {
        fun create(): GetOrgNameUENResponse = GetOrgNameUENResponse()
    }

    var status: Int? = 0
    var message: String? = ""
    var organization_name: String? = ""
    var errors : Errors? = null
}