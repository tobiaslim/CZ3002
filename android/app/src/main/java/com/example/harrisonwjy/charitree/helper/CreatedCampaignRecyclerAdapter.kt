package com.example.harrisonwjy.charitree.helper

import android.content.Intent
import android.os.Build
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.harrisonwjy.charitree.R
import com.example.harrisonwjy.charitree.views.campaignmanager.CreatedCampaignDetailActivity
import com.example.harrisonwjy.charitree.model.Campaign
import kotlinx.android.synthetic.main.item_created_campaign.view.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import android.widget.LinearLayout
import android.widget.TextView
import java.time.LocalDateTime


class CreatedCampaignRecyclerAdapter constructor(private val campaigns: ArrayList<Campaign>?) : RecyclerView.Adapter<CreatedCampaignRecyclerAdapter.ViewHolder>() {


    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.item_created_campaign, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.bindItems(campaigns!!.get(i))
    }

    override fun getItemCount(): Int {
        return campaigns!!.size
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(campaign: Campaign){
            var displayDate: String = ""
            itemView.item_title.text = campaign.name

            displayCampaignImage(campaign.id!!,itemView.item_image)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val startDate = LocalDate.parse(campaign.start_date, DateTimeFormatter.ISO_DATE)
                val endDate = LocalDate.parse(campaign.end_date, DateTimeFormatter.ISO_DATE)
                val formatter = DateTimeFormatter.ofPattern("dd LLL yyyy")
                displayDate = startDate.format(formatter) + " - " + endDate.format(formatter)
            }else{
                displayDate = campaign.start_date + " - " + campaign.end_date
            }

            itemView.item_detail.text = displayDate
            itemView.Pending_items.text = campaign.pending_donations.toString()
            itemView.Ongoing_items.text = campaign.inprogress_donations.toString()
            itemView.Total_items.text = campaign.total_donations.toString()

            // display weather
            val noOfLayers = campaign.weather_forecasts.size
            if (noOfLayers > 0){
                val sizeForEachLayer = 100 / noOfLayers
                for(item in campaign.weather_forecasts){
                    val layout2 = LinearLayout(itemView.context)
                    layout2.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,sizeForEachLayer.toFloat())
                    layout2.setOrientation(LinearLayout.VERTICAL)
                    val tv1 = TextView(itemView.context)
                    val imageView = ImageView(itemView.context)
                    imageView.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)


                    tv1.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        val currentDate = LocalDateTime.now()
                        val formatter = DateTimeFormatter.ofPattern("dd LLL")
                        val current = currentDate.format(formatter)
                        val getDate = LocalDate.parse(item.date, DateTimeFormatter.ISO_DATE)
                        val date = getDate.format(formatter)
                        if(current == date){
                            tv1.text = "Today"
                        }else{
                            tv1.text = date.format(formatter)
                        }

                    }else{
                        tv1.text = item.date
                    }

                    if (item.forecast!!.contains("showers")){
                        imageView.setImageResource(R.drawable.ic_rain)
                    }else{
                        imageView.setImageResource(R.drawable.ic_sun)
                    }
                    tv1.gravity = Gravity.CENTER
                    layout2.addView(tv1)


                    layout2.addView(imageView)
                    itemView.weatherSection.addView(layout2)
                }
            }
            
            itemView.setOnClickListener(object: View.OnClickListener {
                override fun onClick(p0: View?) {
                    val context = itemView.context
                    val intent = Intent(context, CreatedCampaignDetailActivity::class.java).apply{
                        putExtra("campaign",campaign)
                    }
                    context.startActivity(intent)
                }
            })


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