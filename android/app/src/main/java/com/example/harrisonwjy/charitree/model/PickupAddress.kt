package com.example.harrisonwjy.charitree.model

import java.io.Serializable

class PickupAddress : Serializable{
    companion object {
        fun create(): PickupAddress = PickupAddress()
    }

    var id: Int? = 0
    var street_name: String? = null
    var unit: String? = null
    var zip: Int? = 0
    var user_id: Int? = 0
}