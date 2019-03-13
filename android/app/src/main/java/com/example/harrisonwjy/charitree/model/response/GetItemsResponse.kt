package com.example.harrisonwjy.charitree.model.response

import com.example.harrisonwjy.charitree.model.Errors
import com.example.harrisonwjy.charitree.model.Items

class GetItemsResponse {

    companion object {
        fun create(): GetItemsResponse = GetItemsResponse()
    }

    var status: Int? = 0
    var message: String? = null
    var items: ArrayList<Items> = ArrayList()
    var errors: Errors? = null

}