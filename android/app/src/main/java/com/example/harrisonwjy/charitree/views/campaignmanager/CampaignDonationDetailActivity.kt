package com.example.harrisonwjy.charitree.views.campaignmanager

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import com.example.harrisonwjy.charitree.R
import com.example.harrisonwjy.charitree.model.Donation
import com.example.harrisonwjy.charitree.model.request.ChangeStatusDonationRequest
import com.example.harrisonwjy.charitree.repo.CampaignRepo
import com.example.harrisonwjy.charitree.viewmodel.CampaignViewModel
import kotlinx.android.synthetic.main.activity_campaign_donation_detail.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * CampaignDonationDetailActivity class holds the details of the donations
 * @author Wang Lu
 */
class CampaignDonationDetailActivity : AppCompatActivity() {

    private val campaignViewModel : CampaignViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_campaign_donation_detail)

        setSupportActionBar(toolbar)

        val donationId = intent.getIntExtra("donationId",0)

        // put inside viewmodel
        val prefs = getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE)
        val token: String = prefs.getString("token", "")//"No name defined" is the default value.
        val email: String = prefs.getString("email","")

        retrieveData(email,token,donationId)


        // populating data into elements
    }

    fun retrieveData(email: String, token: String, donationId: Int){
        campaignViewModel.getDonationByDID(CampaignRepo(email,token),donationId).observe(this,android.arch.lifecycle.Observer{
            if(it?.status == 1){
                val donation = it.donation
                updateView(donation!!)
                displayStatus(donation.status!!)
                generateButtonsFunction(donation.status!!,token,email,donationId)
            }
        })
    }

    fun updateView(donation: Donation){
        card_view.visibility = View.VISIBLE
        card_view_1.visibility = View.VISIBLE
        card_view_2.visibility = View.VISIBLE
        card_view_3.visibility = View.VISIBLE

        supportActionBar?.title = donation.user!!.first_name +" "+ donation.user!!.last_name + "'s donation"

        displayCampaignImage(donation.Campaign_id!!,donation_detail_background)
        val donatedItem = donation.items
        var text: String? = ""
        for (i in donatedItem!!.indices){
            var tempText = donatedItem!!.get(i).name +" "+ generateQuantityText(donatedItem!!.get(i).qty!!)
            if (i != donatedItem.size.minus(1)){
                text = text + tempText + "\n"
            }else{
                text = text + tempText
            }
        }
        itemAndQuantityTextView.text = text


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val pickUpDate = LocalDate.parse(donation.pickup_date, DateTimeFormatter.ISO_DATE)
            val formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy")
            collectionDateTextView.text = pickUpDate.format(formatter)

        }else{
            collectionDateTextView.text = donation.pickup_date
        }

        collectionTimeSlotTextView.text = donation.pickup_time

        val fullAddress = donation.address!!.street_name.toString() +" "+ donation.address!!.unit.toString() + " Singapore "+ donation.address!!.zip.toString()
        collectionAddressTextView.text = fullAddress

        val status = donation.status!!
        Log.e("DDA","status of donation is "+status)
        if(donation.volunteer_name != null && (status == "In Progress" || status == "Completed")){
            card_view_3.visibility = View.VISIBLE
            volunterDetailsTextView.text = donation.volunteer_name + " (Hp no: "+donation.volunteer_HP+")"

        }else{
            card_view_3.visibility = View.INVISIBLE
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.cancel_donation_menu, menu)
        return true
    }

    //and this to handle actions
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.getItemId()
        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)
    }

    fun generateQuantityText(i: Int): String{
        when(i){
            1 -> {
                return "(Plastic bag)"
            }
            2->{
                return "(Carton box)"
            }
            3->{
                return "(Trolley)"
            }
            else->{
                return "(nil)"
            }
        }
    }

    fun displayStatus(status: String){
        when(status){
            "Pending"->{
                firstSection.setImageResource(R.drawable.highlighted_1)
                secondSection.setImageResource(R.drawable.unhighlighted_2)
                thirdSection.setImageResource(R.drawable.unhighlighted_3)
                forthSection.setImageResource(R.drawable.unhighlighted_4)
            }
            "Approved"->{
                firstSection.setImageResource(R.drawable.ticked)
                secondSection.setImageResource(R.drawable.ticked)
                thirdSection.setImageResource(R.drawable.highlighted_3)
                forthSection.setImageResource(R.drawable.unhighlighted_4)
                approvedRejectTextView.text = "Approved"
            }
            "Rejected"->{
                firstSection.setImageResource(R.drawable.ticked)
                secondSection.setImageResource(R.drawable.rejected)
                thirdSection.setImageResource(R.drawable.unhighlighted_3)
                forthSection.setImageResource(R.drawable.unhighlighted_4)
                approvedRejectTextView.text = "Rejected"
            }
            "In Progress"->{
                firstSection.setImageResource(R.drawable.ticked)
                secondSection.setImageResource(R.drawable.ticked)
                thirdSection.setImageResource(R.drawable.ticked)
                forthSection.setImageResource(R.drawable.highlighted_4)
                approvedRejectTextView.text = "Approved"
            }
            "Completed"->{
                firstSection.setImageResource(R.drawable.ticked)
                secondSection.setImageResource(R.drawable.ticked)
                thirdSection.setImageResource(R.drawable.ticked)
                forthSection.setImageResource(R.drawable.ticked)
                approvedRejectTextView.text = "Approved"
            }
            "Cancelled"->{
                firstSection.setImageResource(R.drawable.rejected)
                secondSection.setImageResource(R.drawable.rejected)
                thirdSection.setImageResource(R.drawable.rejected)
                forthSection.setImageResource(R.drawable.rejected)
            }
            else -> {
                firstSection.setImageResource(R.drawable.rejected)
                secondSection.setImageResource(R.drawable.unhighlighted_2)
                thirdSection.setImageResource(R.drawable.unhighlighted_3)
                forthSection.setImageResource(R.drawable.unhighlighted_4)
            }
        }
    }

    fun generateButtonsFunction(status: String,token: String, email: String, donationId: Int){

        val request = ChangeStatusDonationRequest()
        when(status){
            "Pending"->{
                acceptOrRejectSection.visibility = View.VISIBLE
                assignVolunteerSection.visibility = View.INVISIBLE
                jobCompletedSection.visibility = View.INVISIBLE

                rejectButton.setOnClickListener {
                    request.apply{
                        action = "reject"
                    }
                    sendRequest(email,token,donationId,request)
                }

                approveButton.setOnClickListener {
                    request.apply{
                        action = "approve"
                    }
                    sendRequest(email,token,donationId,request)
                }

            }
            "Approved"->{
                acceptOrRejectSection.visibility = View.INVISIBLE
                assignVolunteerSection.visibility = View.VISIBLE
                jobCompletedSection.visibility = View.INVISIBLE

                assignVolunteerButton.setOnClickListener {
                    val intent = Intent(this, AssignVolunteerActivity::class.java).apply{
                        putExtra("donationId",donationId)
                    }
                    startActivity(intent)
                }
            }
            "Rejected"->{
                acceptOrRejectSection.visibility = View.INVISIBLE
                assignVolunteerSection.visibility = View.INVISIBLE
                jobCompletedSection.visibility = View.INVISIBLE
            }
            "In Progress"->{
                acceptOrRejectSection.visibility = View.INVISIBLE
                assignVolunteerSection.visibility = View.INVISIBLE
                jobCompletedSection.visibility = View.VISIBLE

                jobCompletedButton.setOnClickListener {
                    request.apply{
                        action = "complete"
                    }

                    sendRequest(email,token,donationId,request)
                }
            }
            "Completed"->{
                acceptOrRejectSection.visibility = View.INVISIBLE
                assignVolunteerSection.visibility = View.INVISIBLE
                jobCompletedSection.visibility = View.INVISIBLE
            }
            "Cancelled"->{
                acceptOrRejectSection.visibility = View.INVISIBLE
                assignVolunteerSection.visibility = View.INVISIBLE
                jobCompletedSection.visibility = View.INVISIBLE
            }
            else -> {
                acceptOrRejectSection.visibility = View.INVISIBLE
                assignVolunteerSection.visibility = View.INVISIBLE
                jobCompletedSection.visibility = View.INVISIBLE
            }
        }
    }

    fun sendRequest(email: String, token: String, donationId: Int,request: ChangeStatusDonationRequest){
        campaignViewModel.changeStatusByDID(CampaignRepo(email,token),donationId,request).observe(this,android.arch.lifecycle.Observer{
            if(it?.status == 1){
                Log.e("CampaignDonationDetail","Change of status is Successful")
                retrieveData(email,token,donationId)
            }
        })
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if(hasFocus){
            val donationId = intent.getIntExtra("donationId",0)
            val prefs = getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE)
            val token: String = prefs.getString("token", "")//"No name defined" is the default value.
            val email: String = prefs.getString("email","")
            retrieveData(email, token, donationId)
        }
    }

    fun displayCampaignImage(id: Int, imageView: ImageView){
        val number = id % 8
        var image: Int? = null
        when(number){
            0 -> {
                image = R.drawable.android_image_0
            }
            1 -> {
                image = R.drawable.android_image_1
            }
            2 -> {
                image = R.drawable.android_image_2
            }
            3 -> {
                image = R.drawable.android_image_3
            }
            4 -> {
                image = R.drawable.android_image_4
            }
            5 -> {
                image = R.drawable.android_image_5
            }
            6 -> {
                image = R.drawable.android_image_6
            }
            7 -> {
                image = R.drawable.android_image_7
            }
        }
        imageView.setImageResource(image!!)
    }
}
