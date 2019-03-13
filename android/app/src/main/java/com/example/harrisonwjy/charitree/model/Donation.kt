package com.example.harrisonwjy.charitree.model

import java.io.Serializable

class Donation : Serializable{
    companion object {
        fun create(): Donation = Donation()
    }

    var did: Int? = 0
    var status: String? = null
    var volunteer_name: String? = null
    var volunteer_HP: String? = null
    var pickup_date: String? = null
    var pickup_time: String? = null
    var Campaign_id: Int? = null
    var User_id: Int? = null
    var Address_id: Int? = null
    var items: ArrayList<Items>? = ArrayList()
    var campaign: Campaign? = null
    var address: Address? = null
    var user: User? = null



}