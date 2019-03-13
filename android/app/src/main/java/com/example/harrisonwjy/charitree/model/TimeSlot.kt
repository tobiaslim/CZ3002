package com.example.harrisonwjy.charitree.model

class TimeSlot {
    companion object Factory {
        fun create(): TimeSlot = TimeSlot()
    }
    var start_time : Int? = 0
    var end_time : Int? = 0
    var checked: Boolean? = false
}