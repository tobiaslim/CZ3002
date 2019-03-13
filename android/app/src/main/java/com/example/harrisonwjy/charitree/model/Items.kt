package com.example.harrisonwjy.charitree.model

import java.io.Serializable

class Items : Serializable{
    companion object {
        fun create(): Items = Items()
    }

    var id: Int? = 0
    var name: String? = null
    var qty: Int? = 0
}