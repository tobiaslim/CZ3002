package com.example.harrisonwjy.charitree.repo

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.example.harrisonwjy.charitree.CharitreeApi
import com.example.harrisonwjy.charitree.model.Errors
import com.example.harrisonwjy.charitree.model.request.CreateDonationRequest
import com.example.harrisonwjy.charitree.model.response.CreateDonationResponse
import com.example.harrisonwjy.charitree.model.response.GetAllDonationForUserResponse
import com.example.harrisonwjy.charitree.model.response.GetDonationsCountResponse
import com.example.harrisonwjy.charitree.repo.interfaces.DonationInterface
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

/**
 * A DonationRepo class to execute donation related APIs
 * @author Harrison Wong
 */
class DonationRepo(email: String, token: String) : DonationInterface{

    private val api: CharitreeApi

    companion object {
        fun newInstance(email: String,token: String) : DonationRepo{
            return DonationRepo(email,token)
        }
    }

    init {
        val okHttpClient = OkHttpClient().newBuilder().addInterceptor(object : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
                val originalRequest = chain.request()

                val builder = originalRequest.newBuilder().header("Authorization",
                        Credentials.basic(email, token))

                val newRequest = builder.build()
                return chain.proceed(newRequest)
            }
        }).build()

        val retrofit = Retrofit.Builder()
                .baseUrl(CharitreeApi.API_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        api = retrofit.create(CharitreeApi::class.java)
    }


    /**
     * A method to retrieve all donation by the user
     */
    override fun getAll(): Any {
        val data = MutableLiveData<GetAllDonationForUserResponse>()
        api.getUserDonations().enqueue(
                object: Callback<GetAllDonationForUserResponse> {
                    val userDonationsResponse = GetAllDonationForUserResponse()
                    override fun onResponse(call: Call<GetAllDonationForUserResponse>?, response: Response<GetAllDonationForUserResponse>?) {

                        Log.e("CampaignRepo","Resposne code is "+ response!!.code().toString())
                        if(response!!.isSuccessful){
                            Log.e("CampaignRepo","successful call")
                            data.value = response.body()

                        }else{
                            //Log.e("CampaignRepo","failed: "+response.errorBody().string())
                            when(response.code()){
                                500 -> {
                                    userDonationsResponse.apply{
                                        status = 0
                                        errors = Errors().apply{
                                            message = "Server error. Please contact adminstrator"
                                        }
                                    }
                                }
                                404 -> {
                                    userDonationsResponse.apply{
                                        status = 0
                                        errors = Errors().apply{
                                            message = "No created donations created by you"
                                        }
                                    }
                                }
                                else -> {
                                    val jObjError = JSONObject(response.errorBody().string())

                                    val getMessage = jObjError.optJSONObject("errors")?.optString("message")

                                    userDonationsResponse.apply {
                                        status = jObjError.getString("status").toInt()
                                        errors = Errors().apply {
                                            message = getMessage
                                        }
                                    }
                                }
                            }
                        }

                        data.value = userDonationsResponse;
                    }


                    override fun onFailure(call: Call<GetAllDonationForUserResponse>?, t: Throwable?) {
                        Log.e("LoginResponse","Unable to submit email and password to API")
                        userDonationsResponse.apply{
                            status = null
                            errors = null
                        }
                        data.value = userDonationsResponse
                    }
                }
        )
        return data
    }

    /**
     * A method to create a donation request
     */
    override fun create(id: Int, item: Any): Any {
        val getItem: CreateDonationRequest = item as CreateDonationRequest
        val data = MutableLiveData<CreateDonationResponse>()
        api.createDonation(id,getItem).enqueue(
                object: Callback<CreateDonationResponse> {
                    val donationResponse = CreateDonationResponse()
                    override fun onResponse(call: Call<CreateDonationResponse>?, response: Response<CreateDonationResponse>?) {
                        Log.e("createDonation","Successful: Response code is "+ response!!.code().toString())
                        if(response!!.isSuccessful){
                            data.value = response.body()

                        }else{
                            Log.e("createDonation","Error: Response code is "+ response!!.code().toString())
                            when(response.code()){
                                500 -> {
                                    donationResponse.apply{
                                        status = 0
                                        errors = Errors().apply{
                                            message = "Server error. Please contact adminstrator"
                                        }
                                    }
                                }
                                404 -> {
                                    donationResponse.apply{
                                        status = 0
                                        errors = Errors().apply{
                                            message = "No address created by you"
                                        }
                                    }
                                }
                                else -> {
                                    val jObjError = JSONObject(response.errorBody().string())

                                    //Log.e("createDonation",jObjError.toString())
                                    val getMessage = jObjError.optJSONObject("errors")?.optString("message")

                                    donationResponse.apply {
                                        status = jObjError.getString("status").toInt()
                                        errors = Errors().apply {
                                            message = getMessage
                                        }
                                    }
                                }
                            }
                        }

                        data.value = donationResponse;
                    }


                    override fun onFailure(call: Call<CreateDonationResponse>?, t: Throwable?) {
                        Log.e("LoginResponse","Unable to submit email and password to API")
                        donationResponse.apply{
                            status = 0
                            errors = null
                        }
                        data.value = donationResponse
                    }
                }
        )
        return data
    }

    /**
     * A method to get the count of donation donated by the user
     */
    override fun getCount(item: Any): Any {
        val getItem = item.toString()
        val data = MutableLiveData<GetDonationsCountResponse>()
        api.getNoOfDonation(getItem).enqueue(
                object: Callback<GetDonationsCountResponse> {
                    val getDonationCountResponse = GetDonationsCountResponse()
                    override fun onResponse(call: Call<GetDonationsCountResponse>?, response: Response<GetDonationsCountResponse>?) {

                        Log.e("CampaignRepo","Resposne code is "+ response!!.code().toString())
                        if(response!!.isSuccessful){
                            Log.e("CampaignRepo","successful call")
                            Log.e("CampaignRepo",response.body().toString())
                            data.value = response.body()

                        }else{
                            //Log.e("CampaignRepo","failed: "+response.errorBody().string())
                            when(response.code()){
                                500 -> {
                                    getDonationCountResponse.apply{
                                        status = 0
                                        errors = Errors().apply{
                                            message = "Server error. Please contact adminstrator"
                                        }
                                    }
                                }
                                404 -> {
                                    getDonationCountResponse.apply{
                                        status = 0
                                        errors = Errors().apply{
                                            message = "No donors list for this campaign in database"
                                        }
                                    }
                                }
                                else -> {
                                    val jObjError = JSONObject(response.errorBody().string())

                                    val getMessage = jObjError.optJSONObject("errors")?.optString("message")

                                    getDonationCountResponse.apply {
                                        status = jObjError.getString("status").toInt()
                                        errors = Errors().apply {
                                            message = getMessage
                                        }
                                    }
                                }
                            }
                        }

                        data.value = getDonationCountResponse;
                    }


                    override fun onFailure(call: Call<GetDonationsCountResponse>?, t: Throwable?) {
                        Log.e("LoginResponse","Unable to submit email and password to API")
                        getDonationCountResponse.apply{
                            status = null
                            errors = null
                        }
                        data.value = getDonationCountResponse
                    }
                }
        )
        return data
    }


}