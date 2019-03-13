package com.example.harrisonwjy.charitree.views

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.CardView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.harrisonwjy.charitree.R
import com.example.harrisonwjy.charitree.model.AcceptedItem
import com.example.harrisonwjy.charitree.model.Campaign
import kotlinx.android.synthetic.main.fragment_campaign_item.view.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.*

/**
 * A CampaignItemFragment holds the UI of the list of campaigns screen
 * @author Harrison Wong, Wang Lu
 *
 */
class CampaignItemFragment: Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_campaign_item, container, false)

        val campaign = arguments?.getSerializable("campaign") as Campaign

        val cardview = view.findViewById<CardView>(R.id.cardView)

        var itemNeeded: String? = ""

        for (item in campaign.accepted_items as ArrayList<AcceptedItem>){
            Log.e("CIF",item.value + "->"+itemNeeded)
            itemNeeded = item.value + " "+ itemNeeded
        }

        val imageView = view.findViewById<ImageView>(R.id.imageView)
        displayCampaignImage(campaign.id!!,imageView)

        var daysLeft: String? = null
        //val startDate = Date(campaign.start_date)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val todayDate = LocalDate.now()
            val endDate = LocalDate.parse(campaign.end_date, DateTimeFormatter.ISO_DATE)
            val daysLeft = ChronoUnit.DAYS.between(todayDate,endDate).toString()
            Log.e("CIF","date is "+todayDate.toString() + " and " + endDate.toString() + " = " +daysLeft)
            view.daysLeftText.text = campaign.days_left.toString()
        }

        view.apply{
            title.text = campaign.name
            itemsNeededText.text = itemNeeded
        }

        cardview.setOnClickListener {
            val intent = Intent(activity, CampaignDetailActivity::class.java)
            intent.putExtra("campaign",campaign)
            startActivity(intent)
        }

        return view
    }

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