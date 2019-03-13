package com.example.harrisonwjy.charitree.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.example.harrisonwjy.charitree.model.request.RegisterCMRequest
import com.example.harrisonwjy.charitree.model.response.CMRegisterResponse
import com.example.harrisonwjy.charitree.model.response.CMVerifyResponse
import com.example.harrisonwjy.charitree.model.response.GetOrgNameUENResponse
import com.example.harrisonwjy.charitree.repo.interfaces.CampaignInterface

/**
 * Two way binding ViewModel to handle campaign manager tasks
 * @author Harrison wong
 */
class CampaignManagerViewModel : ViewModel() {



    fun register(repo: CampaignInterface, request: RegisterCMRequest): LiveData<CMRegisterResponse>{
        return repo.register(request) as LiveData<CMRegisterResponse>
    }

    fun getCampaignManagerAccess(repo: CampaignInterface, request: RegisterCMRequest) : LiveData<CMVerifyResponse>? {
        return repo.verify(request) as LiveData<CMVerifyResponse>
    }

    fun getOrgNameByUEN(repo: CampaignInterface, uen: String) : LiveData<GetOrgNameUENResponse>{
        return repo.getOrgNameByUEN(uen) as LiveData<GetOrgNameUENResponse>
    }


    // get all requested items

    // create campaign

    // view my campaigns (campaigner manger side)

    // view all donations by that specifci campaign

    // view specific donation

    // approve reject, assign volunteers, cancel


}

