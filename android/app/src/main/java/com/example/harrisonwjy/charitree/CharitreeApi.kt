package com.example.harrisonwjy.charitree

import com.example.harrisonwjy.charitree.model.request.*
import com.example.harrisonwjy.charitree.model.response.*
import retrofit2.Call
import retrofit2.http.*

/**
 * An interface with API methods to the Charitree server
 * @author Harrison wong, Wang Lu
 * For more information on the API, please refer to the Charitree-server files/documentation
 */
interface CharitreeApi {


    // register
    /**
     * A POST method to register as Normal User (donor)
     */
    @POST("users")
    @Headers("Content-Type: application/json")
    fun register(@Body request:UserRegisterRequest)
                 : Call<UserRegisterResponse>

    /**
     * A POST method for users to login into the application
     */
    @POST("sessions")
    @Headers("Content-Type: application/json")
    fun login(@Body request: LoginRequest)
            : Call<LoginResponse>


    /**
     * A POST method for users to register as a Campaign Manager
     */
    @POST("users/campaignmanagers")
    @Headers("Content-Type: application/json")
    fun registerCM(@Body request: RegisterCMRequest)
                : Call<CMRegisterResponse>

    /**
     * A GET method for a user to retrieve the organisation name using the Unique Entity Number (UEN)
     */
    @GET("uen")
    @Headers("Content-Type: application/json")
    fun getOrgNameByUEN(@Query("uen") uen: String)
                : Call<GetOrgNameUENResponse>

    /**
     * A GET method to verify whether the user is a campaign manager
     */
    @GET("users/campaignmanagers")
    @Headers("Content-Type: application/json")
    fun verifyCM(): Call<CMVerifyResponse>

    /**
     * A GET method to retrieve a  list of current and future Campaigns and its details such as description and weather
     */
    @GET("campaigns")
    @Headers("Content-Type: application/json")
    fun getCampaigns(): Call<GetCampaignsResponse>

    /**
     * A GET method to retrieve a list of campaigns and its details created by the campaign manager
     */
    @GET("campaigns/campaignmanagers")
    @Headers("Content-Type: application/json")
    fun getCampaignsByCMSession(): Call<GetCampaignsByCMSession>

    /**
     * A GET method to retrieve the addresses of the user
     */
    @GET("addresses")
    @Headers("Content-Type: application/json")
    fun getAddresses(): Call<GetAddressResponse>

    /**
     * A POST method to create an address
     */
    @POST("addresses")
    @Headers("Content-Type: application/json")
    fun createAddresses(@Body request: GetAddressRequest)
            : Call<GetAddressResponse>

    /**
     * A POST method to create a donation request for a specific campaign
     */
    @POST("donations/campaigns/{campaignId}")
    @Headers("Content-Type: application/json")
    fun createDonation(@Path("campaignId") campaignId: Int, @Body request: CreateDonationRequest)
            : Call<CreateDonationResponse>


    /**
     * A GET method to retrieve all the user's donation
     */
    @GET("donations")
    @Headers("Content-Type: application/json")
    fun getUserDonations(): Call<GetAllDonationForUserResponse>

    /**
     * A POST method to create a campaign
     */
    @POST("campaigns")
    @Headers("Content-Type: application/json")
    fun createCampaign(@Body request: CreateCampaignRequest)
            : Call<CreateCampaignResponse>

    /**
     * A GET method to retrieve the available list of items that can be choosen by the campaign manager during the creation of campaign
     */
    @GET("items")
    @Headers("Content-Type: application/json")
    fun getItems(): Call<GetItemsResponse>

    // get list of donations by campaign id (3vi)
    /**
     * A GET method to retrieve the list of donors by the specific campaign
     */
    @GET("donations/campaignmanagers/campaigns/{campaignId}")
    @Headers("Content-Type: application/json")
    fun getListOfDonationsByCID(@Path("campaignId") campaignId: Int)
            :Call<GetListOfDonationsByCIDResponse>


    /**
     * A GET method to retrieve the details of a donation
     */
    @GET("donations/{donationId}/campaignmanagers")
    @Headers("Content-Type: application/json")
    fun getDonationByDID(@Path("donationId") donationId: Int)
            :Call<GetDonationByDonationIDResponse>


    /**
     * A PATCH method to update the status of the donation
     */
    @PATCH("donations/{donationId}/campaignmanagers")
    @Headers("Content-Type: application/json")
    fun editStatusByDID(@Path("donationId") donationId: Int, @Body request: ChangeStatusDonationRequest)
            : Call<ChangeStatusDonationResponse>


    /**
     * A GET method to retireve the no of donation by the user
     */
    @GET("donations/count")
    @Headers("Content-Type: application/json")
    fun getNoOfDonation(@Query("countBy") countBy: String)
            : Call<GetDonationsCountResponse>


    /**
     * A GET method to check whether the session is valid
     */
    @GET("sessions")
    @Headers("Content-Type: application/json")
    fun checkSessionIsValid()
            : Call<GetSessionsResponse>


    /**
     * An object declaration with a parameter to hold the IP Address
     */
    companion object {
        // Local server
        //val API_URL = "http://10.0.2.2/public/"

        // School IP address
        val API_URL = "http://172.21.148.170/Charitree/public/"
    }





}