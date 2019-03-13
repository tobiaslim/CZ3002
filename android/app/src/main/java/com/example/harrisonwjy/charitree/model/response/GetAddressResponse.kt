package com.example.harrisonwjy.charitree.model.response

import com.example.harrisonwjy.charitree.model.Address
import com.example.harrisonwjy.charitree.model.Errors

class GetAddressResponse {
    companion object Factory {
        fun create(): GetAddressResponse = GetAddressResponse()
    }

    var status: Int? = 0
    var message: String? = null
    var errors: Errors? = null
    var addresses: ArrayList<Address>? = null


}