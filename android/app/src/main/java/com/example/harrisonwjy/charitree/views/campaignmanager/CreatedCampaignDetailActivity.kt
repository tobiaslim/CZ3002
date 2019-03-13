package com.example.harrisonwjy.charitree.views.campaignmanager

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toolbar
import com.example.harrisonwjy.charitree.R
import com.example.harrisonwjy.charitree.model.AcceptedItem
import com.example.harrisonwjy.charitree.model.Campaign
import kotlinx.android.synthetic.main.activity_campaign_detail.*

import kotlinx.android.synthetic.main.activity_created_campaign_detail.*
import org.w3c.dom.Text
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * CreatedCampaignDetailActivity class holds the UI of details of the campaign
 * @author Wang Lu
 */
class CreatedCampaignDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_created_campaign_detail)


        val campaign = intent.getSerializableExtra("campaign") as Campaign


        val toolbar = findViewById<android.support.v7.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = campaign.name


        val image_displayed = findViewById<ImageView>(R.id.image_displayed)
        val textView_CampName = findViewById<TextView>(R.id.textView_CampName)
        val textView_time = findViewById<TextView>(R.id.textView_time)
        val textView_description = findViewById<TextView>(R.id.textView_description)
        val textView_startDate = findViewById<TextView>(R.id.textView_startDate)
        val textView_endDate = findViewById<TextView>(R.id.textView_endDate)
        val textView_items = findViewById<TextView>(R.id.textView_items)
        val textView_address = findViewById<TextView>(R.id.textView_address)

        displayCampaignImage(campaign.id!!,image_displayed)
        //image_displayed.setImageResource(R.drawable.give_image) // set image
        textView_CampName.text = campaign.name // set name
        val timing: String = campaign.start_time.toString() + "00HRS -" + campaign.end_time.toString()+"00HRS"
        textView_time.text = timing
        textView_description.text = campaign.description

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val startDate = LocalDate.parse(campaign.start_date, DateTimeFormatter.ISO_DATE)
            val endDate = LocalDate.parse(campaign.end_date, DateTimeFormatter.ISO_DATE)
            val formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy")
            textView_startDate.text = startDate.format(formatter)
            textView_endDate.text = endDate.format(formatter)

        }else{
            textView_startDate.text = campaign.start_date
            textView_endDate.text = campaign.end_date
        }



        var itemNeeded: String? = ""

        for (item in campaign.accepted_items as ArrayList<AcceptedItem>){
            Log.e("CIF",item.value + "->"+itemNeeded)
            itemNeeded = item.value + " "+ itemNeeded
        }
        textView_items.text = itemNeeded
        val address = campaign.collection_point + " Singapore "+campaign.postal_code
        textView_address.text = address

        ViewDonation.setOnClickListener(object: View.OnClickListener {
            override fun onClick(view: View): Unit {
                val intent = Intent(this@CreatedCampaignDetailActivity, DonorListActivity::class.java).apply{
                    putExtra("campaignId",campaign.id!!)
                }
                startActivity(intent)
            }

            // need pass data
        })

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
