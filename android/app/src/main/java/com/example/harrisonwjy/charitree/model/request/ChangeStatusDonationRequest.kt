package com.example.harrisonwjy.charitree.model.request

class ChangeStatusDonationRequest {
    companion object {
        fun create(): ChangeStatusDonationRequest = ChangeStatusDonationRequest()
    }

    var action: String? = null // ['approve', 'reject', 'cancel', 'in-progress','complete']
    var volunteer_name: String? = null
    var volunteer_HP: String? = null
}