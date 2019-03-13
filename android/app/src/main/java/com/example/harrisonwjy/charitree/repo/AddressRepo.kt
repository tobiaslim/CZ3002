package com.example.harrisonwjy.charitree.repo

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.example.harrisonwjy.charitree.CharitreeApi
import com.example.harrisonwjy.charitree.model.Errors
import com.example.harrisonwjy.charitree.model.request.GetAddressRequest
import com.example.harrisonwjy.charitree.model.response.GetAddressResponse
import com.example.harrisonwjy.charitree.repo.interfaces.AddressInterface
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
 * A AddressRepo class to execute address related APIs
 * @author Harrison Wong
 */
class AddressRepo(email: String, token: String) : AddressInterface{


    private val TAG = AddressRepo::class.java.name
    private val api: CharitreeApi

    companion object {
        fun newInstance(email: String,token: String) : AddressRepo{
            return AddressRepo(email,token)
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
     * A method to retrieve a list of addresses with a return of GetAddressResponse
     */
    override fun get(): Any {
        val data = MutableLiveData<GetAddressResponse>()
        api.getAddresses().enqueue(
                object: Callback<GetAddressResponse> {
                    val campaignsResponse = GetAddressResponse()
                    override fun onResponse(call: Call<GetAddressResponse>?, response: Response<GetAddressResponse>?) {
                        if(response!!.isSuccessful){
                            Log.e(TAG,"get() successfull")
                            data.value = response.body()

                        }else{
                            Log.e(TAG,"get() failed")
                            when(response.code()){
                                500 -> {
                                    campaignsResponse.apply{
                                        status = 0
                                        errors = Errors().apply{
                                            message = "Server error. Please contact adminstrator"
                                        }
                                    }
                                }
                                404 -> {
                                    campaignsResponse.apply{
                                        status = 0
                                        errors = Errors().apply{
                                            message = "No created campaign created by you"
                                        }
                                    }
                                }
                                else -> {
                                    val jObjError = JSONObject(response.errorBody().string())

                                    val getMessage = jObjError.optJSONObject("errors")?.optString("message")

                                    campaignsResponse.apply {
                                        status = jObjError.getString("status").toInt()
                                        errors = Errors().apply {
                                            message = getMessage
                                        }
                                    }
                                }
                            }
                        }

                        data.value = campaignsResponse;
                    }


                    override fun onFailure(call: Call<GetAddressResponse>?, t: Throwable?) {
                        Log.e(TAG,"get() failed to send")
                        campaignsResponse.apply{
                            status = null
                            errors = null
                        }
                        data.value = campaignsResponse
                    }
                }
        )
        return data
    }

    /**
     * A method to create address with a return of GetAddressResponse
     */
    override fun create(item: Any): Any {
        val getItem: GetAddressRequest = item as GetAddressRequest

        val data = MutableLiveData<GetAddressResponse>()
        api.createAddresses(getItem).enqueue(
                object: Callback<GetAddressResponse> {
                    val campaignsResponse = GetAddressResponse()
                    override fun onResponse(call: Call<GetAddressResponse>?, response: Response<GetAddressResponse>?) {
                        Log.e("CampaignRepo","Resposne code is "+ response!!.code().toString())
                        if(response!!.isSuccessful){
                            data.value = response.body()

                        }else{
                            Log.e("createAddress",response.errorBody().string())
                            when(response.code()){
                                500 -> {
                                    campaignsResponse.apply{
                                        status = 0
                                        errors = Errors().apply{
                                            message = "Server error. Please contact adminstrator"
                                        }
                                    }
                                }
                                404 -> {
                                    campaignsResponse.apply{
                                        status = 0
                                        errors = Errors().apply{
                                            message = "No address created by you"
                                        }
                                    }
                                }
                                else -> {
                                    val jObjError = JSONObject(response.errorBody().string())

                                    val getMessage = jObjError.optJSONObject("errors")?.optString("message")

                                    campaignsResponse.apply {
                                        status = jObjError.getString("status").toInt()
                                        errors = Errors().apply {
                                            message = getMessage
                                        }
                                    }
                                }
                            }
                        }

                        data.value = campaignsResponse;
                    }


                    override fun onFailure(call: Call<GetAddressResponse>?, t: Throwable?) {
                        Log.e("LoginResponse","Unable to submit email and password to API")
                        campaignsResponse.apply{
                            status = null
                            errors = null
                        }
                        data.value = campaignsResponse
                    }
                }
        )
        return data
    }


}