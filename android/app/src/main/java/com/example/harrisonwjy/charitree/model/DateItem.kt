package com.example.harrisonwjy.charitree.model

class DateItem {
    companion object Factory {
        fun create(): DateItem = DateItem()
    }
    var day: Int? = 0
    var month: String? = null
    var actualDate: String? = null
}