package com.example.harrisonwjy.charitree.model.request

import com.example.harrisonwjy.charitree.model.Address

class GetAddressRequest {
    companion object Factory {
        fun create(): GetAddressRequest = GetAddressRequest()
    }

    var addresses: ArrayList<Address>? = ArrayList()
}