package com.example.harrisonwjy.charitree.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.example.harrisonwjy.charitree.model.Donation
import com.example.harrisonwjy.charitree.model.request.CreateDonationRequest
import com.example.harrisonwjy.charitree.model.request.GetAddressRequest
import com.example.harrisonwjy.charitree.model.response.CreateDonationResponse
import com.example.harrisonwjy.charitree.model.response.GetAddressResponse
import com.example.harrisonwjy.charitree.model.response.GetAllDonationForUserResponse
import com.example.harrisonwjy.charitree.model.response.GetDonationsCountResponse
import com.example.harrisonwjy.charitree.repo.AddressRepo
import com.example.harrisonwjy.charitree.repo.CampaignRepo
import com.example.harrisonwjy.charitree.repo.DonationRepo

/**
 * Two way binding ViewModel to handle donation task
 * @author Harrison wong
 */
class DonationViewModel : ViewModel(){

    // view my donations

    fun getAllDonationForUser(repo: DonationRepo): LiveData<GetAllDonationForUserResponse> {
        return repo.getAll() as LiveData<GetAllDonationForUserResponse>
    }

    fun getDonationCount(repo: DonationRepo, status: String): LiveData<GetDonationsCountResponse>{
        return repo.getCount(status) as LiveData<GetDonationsCountResponse>
    }
    // create donation
    fun createDonation(repo: DonationRepo, campaignId: Int, request: CreateDonationRequest): LiveData<CreateDonationResponse>{
        return repo.create(campaignId,request) as LiveData<CreateDonationResponse>
    }

    fun getAddress(repo: AddressRepo): LiveData<GetAddressResponse>{
        return repo.get() as LiveData<GetAddressResponse>
    }

    fun createAddress(repo: AddressRepo, request: GetAddressRequest): LiveData<GetAddressResponse>{
        return repo.create(request) as LiveData<GetAddressResponse>
    }

}