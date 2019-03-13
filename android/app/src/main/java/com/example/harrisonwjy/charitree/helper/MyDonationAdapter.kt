package com.example.harrisonwjy.charitree.helper

import android.content.Intent
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.harrisonwjy.charitree.R
import com.example.harrisonwjy.charitree.model.Donation
import com.example.harrisonwjy.charitree.views.DonationDetailActivity
import kotlinx.android.synthetic.main.item_donation_card.view.*


class MyDonationAdapter constructor(private val donations: ArrayList<Donation>?) : RecyclerView.Adapter<MyDonationAdapter.ViewHolder>() {


    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.item_donation_card, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.bindItems(donations!!.get(i))
    }

    override fun getItemCount(): Int {
        return donations!!.size
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun bindItems(donation: Donation){
            itemView.campaignName.text = donation.campaign!!.name

            val donatedItems = donation.items
            var items: String? = ""
            for (item in donatedItems!!){
                items = item.name + " "+ items
            }

            itemView.donatedItems.text= items
            itemView.Date.text= donation.pickup_date
            itemView.Time.text = donation.pickup_time
            itemView.Address.text= donation.address!!.street_name
            itemView.itemsIcon.setImageResource(R.drawable.list_black_24dp)
            itemView.dateIcon.setImageResource(R.drawable.date_range_black_24dp)
            itemView.timeIcon.setImageResource(R.drawable.time_black_24dp)
            itemView.addressIcon.setImageResource(R.drawable.location_24dp)
            itemView.timePassed.text = donation.status

            displayStatusColor(donation.status!!)


            itemView.setOnClickListener(object: View.OnClickListener {
                override fun onClick(p0: View?) {
                    val context = itemView.context
                    val intent = Intent(context, DonationDetailActivity::class.java).apply{
                        putExtra("donation",donation)
                    }
                    context.startActivity(intent)
                }
            })
        }

        fun displayStatusColor(status: String){
            when(status){
                "Pending"->{
                    itemView.timePassed.setTextColor(Color.parseColor("#78909C"))
                }
                "Approved"->{
                    itemView.timePassed.setTextColor(Color.parseColor("#76FF03"))
                }
                "Rejected"->{
                    itemView.timePassed.setTextColor(Color.parseColor("#F50057"))
                }
                "In Progress"->{
                    itemView.timePassed.setTextColor(Color.parseColor("#3D5AFE"))
                }
                "Completed"->{
                    itemView.timePassed.setTextColor(Color.parseColor("#00E676"))
                }
                "Cancelled"->{
                    itemView.timePassed.setTextColor(Color.parseColor("#F50057"))
                }else -> {
                    itemView.timePassed.setTextColor(Color.parseColor("#78909C"))
            }
            }
        }

    }


}