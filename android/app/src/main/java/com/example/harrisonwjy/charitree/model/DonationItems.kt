package com.example.harrisonwjy.charitree.model

class DonationItems {
    companion object Factory {
        fun create(): DonationItems = DonationItems()
    }

    var keys: ArrayList<Int> = ArrayList()
    var values: ArrayList<Int> = ArrayList()
}