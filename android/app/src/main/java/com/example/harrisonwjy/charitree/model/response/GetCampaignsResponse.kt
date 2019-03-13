package com.example.harrisonwjy.charitree.model.response

import com.example.harrisonwjy.charitree.model.Campaign
import com.example.harrisonwjy.charitree.model.Errors

class GetCampaignsResponse {
    companion object Factory {
        fun create(): GetCampaignsResponse = GetCampaignsResponse()
    }

    var status: Int? = 0
    var messages: String? = null
    var errors: Errors? = null
    var campaigns: ArrayList<Campaign>? = ArrayList()
}

//class Item {
//
//    var keys: ArrayList<Int>? = ArrayList()
//    var values: ArrayList<Int>? = ArrayList()
//}
//
//class adasdasresponse{
//
//    var addressId: String? = ""
//    var item: Item? = null
//}