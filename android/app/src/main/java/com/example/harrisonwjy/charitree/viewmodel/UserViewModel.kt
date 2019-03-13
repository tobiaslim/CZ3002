package com.example.harrisonwjy.charitree.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.example.harrisonwjy.charitree.model.request.LoginRequest
import com.example.harrisonwjy.charitree.model.request.UserRegisterRequest
import com.example.harrisonwjy.charitree.model.response.*
import com.example.harrisonwjy.charitree.repo.AuthenticationRepo
import com.example.harrisonwjy.charitree.repo.interfaces.LoginAndRegisterInterface

/**
 * Two way binding ViewModel to handle UserViewModel tasks
 * @author Harrison wong
 */
class UserViewModel : ViewModel() {



    fun authenticate(repo: LoginAndRegisterInterface, request: LoginRequest) : LiveData<LoginResponse>{
        //val data = MutableLiveData<LoginResponse>()
        return repo.verify(request) as LiveData<LoginResponse>
    }

    fun register(repo: LoginAndRegisterInterface, request: UserRegisterRequest) : LiveData<UserRegisterResponse>{
        //val data = MutableLiveData<LoginResponse>()
        return repo.register(request) as LiveData<UserRegisterResponse>
    }

    fun authenticateSession(repo: AuthenticationRepo): LiveData<GetSessionsResponse>{
        return repo.verify() as LiveData<GetSessionsResponse>
    }
}

