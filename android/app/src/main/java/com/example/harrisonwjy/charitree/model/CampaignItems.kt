package com.example.harrisonwjy.charitree.model

import java.io.Serializable

class CampaignItems : Serializable{

    companion object {
        fun create(): CampaignItems = CampaignItems()
    }

    var key: Int? = null
    var item_name: String? = null
    var choiceNumber: Int? = null
    var checked: Boolean? = false
    var quantity: Int? = 0

}