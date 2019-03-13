package com.example.harrisonwjy.charitree.repo

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.example.harrisonwjy.charitree.CharitreeApi
import com.example.harrisonwjy.charitree.model.Errors
import com.example.harrisonwjy.charitree.model.response.GetSessionsResponse
import com.example.harrisonwjy.charitree.repo.interfaces.AuthenticationInterface
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
 * A AuthenticationRepo class to execute Authentication related APIs
 * @author Harrison Wong
 */
class AuthenticationRepo(email: String, token: String) : AuthenticationInterface {

    private val api: CharitreeApi

    companion object {
        fun newInstance(email: String,token: String) : AuthenticationRepo{
            return AuthenticationRepo(email,token)
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
     * A method to verify the current session token with a return of GetSessionResponse
     */
    override fun verify(): Any {
        val data = MutableLiveData<GetSessionsResponse>()
        // call login method from CharitreeApi interface
        api.checkSessionIsValid().enqueue(
                object: Callback<GetSessionsResponse> {
                    val campaignsResponse = GetSessionsResponse()
                    override fun onResponse(call: Call<GetSessionsResponse>?, response: Response<GetSessionsResponse>?) {

                        if(response!!.isSuccessful){
                            Log.e("AuthenticationRepo","verify successfull")

                            data.value = response.body()


                        }else{
                            if(response.code() == 500){
                                campaignsResponse.apply{
                                    status = 0
                                    errors = Errors().apply{
                                        message = "Server error. Please contact adminstrator"
                                    }
                                }
                            }else {

                                val jObjError = JSONObject(response.errorBody().string())

                                val getMessage = jObjError.optJSONObject("errors")?.optString("message")

                                campaignsResponse.apply {
                                    status = jObjError.getString("status").toInt()
                                    errors = Errors().apply {
                                        message = getMessage
                                    }
                                }
                            }

                            data.value = campaignsResponse;
                        }
                    }

                    override fun onFailure(call: Call<GetSessionsResponse>?, t: Throwable?) {
                        Log.e("AuthenticationRepo","Unable to submit email and password to API")
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