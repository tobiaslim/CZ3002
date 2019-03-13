package com.example.harrisonwjy.charitree.views

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
import kotlinx.android.synthetic.main.activity_donation_detail.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * DonationDetailActivity class holds the UI for the details of the donation
 *@author Wang Lu
 */
class DonationDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donation_detail)

        setSupportActionBar(toolbar)


        val donation = intent.getSerializableExtra("donation") as Donation
        supportActionBar?.title = donation.campaign!!.name

        val donation_detail_background = findViewById<ImageView>(R.id.donation_detail_background)

        displayCampaignImage(donation!!.campaign!!.id!!,donation_detail_background)

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

        displayStatus(donation.status!!)

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.cancel_donation_menu, menu)
        return true
    }

    //and this to handle actions
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.getItemId()
        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)
    }

    /**
     * A function to generate a string based on the quantity
     */
    private fun generateQuantityText(i: Int): String{
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

    /**
     * A function to display the status UI based on the retrieved status
     */
    private fun displayStatus(status: String){
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

    /**
     * A function that displays the image based on the campaign id
     */
    private fun displayCampaignImage(id: Int, imageView: ImageView){
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
