package com.example.harrisonwjy.charitree.model

import com.example.harrisonwjy.charitree.model.response.GetAddressResponse
import java.io.Serializable

class Address :Serializable{
    companion object Factory {
        fun create(): Address = Address()
    }

    var id: Int? = 0
    var street_name: String? = null
    var unit: String? = null
    var zip: String? = null
    var user_id : Int? = null
}