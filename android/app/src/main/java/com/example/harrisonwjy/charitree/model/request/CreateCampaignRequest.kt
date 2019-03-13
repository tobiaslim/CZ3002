package com.example.harrisonwjy.charitree.model.request

import com.example.harrisonwjy.charitree.model.AcceptedItem

class CreateCampaignRequest {
    companion object {
        fun create(): CreateCampaignRequest = CreateCampaignRequest()
    }

    var name: String? = null
    var start_date: String? = null // yyyy-mm-dd format
    var end_date: String? = null // yyyy-mm-dd format
    var accepted_items: ArrayList<Int> = ArrayList()
    var start_time: Int? = null
    var end_time: Int? = null
    var description: String? = null
    var collection_point: String? = null
    var postal_code: String? = null


}