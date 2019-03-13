package com.example.harrisonwjy.charitree.model

// error class

class Errors{
    companion object {
        fun create(): Errors = Errors()
    }
    var email: ArrayList<String>? = ArrayList()
    var first_name: ArrayList<String>? = ArrayList()
    var last_name: ArrayList<String>? = ArrayList()
    var password: ArrayList<String>? = ArrayList()

    var UEN: ArrayList<String?> = ArrayList()
    var organization_name: ArrayList<String>? = ArrayList()
    var message: String? = null
}