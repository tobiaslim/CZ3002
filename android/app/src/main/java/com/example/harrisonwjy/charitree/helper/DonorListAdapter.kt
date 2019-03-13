package com.example.harrisonwjy.charitree.helper

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.harrisonwjy.charitree.R
import com.example.harrisonwjy.charitree.views.campaignmanager.CampaignDonationDetailActivity
import com.example.harrisonwjy.charitree.model.Donation
import kotlinx.android.synthetic.main.item_donor.view.*


class DonorListAdapter constructor(private val donors: ArrayList<Donation>?) : RecyclerView.Adapter<DonorListAdapter.ViewHolder>() {


    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_donor, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.bindItems(donors!!.get(i))
    }

    override fun getItemCount(): Int {
        return donors!!.size
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(donor: Donation){
            itemView.donar_name.text = donor.user!!.first_name +" "+donor.user!!.last_name
            itemView.donationStatus.text = donor.status

            var items: String? = ""
            for(item in donor.items!!){
                items = item.name + " " + items
            }
            itemView.item_donated1.text = items


            itemView.setOnClickListener(object: View.OnClickListener {
                override fun onClick(p0: View?) {
                    val context = itemView.context
                    val intent = Intent(context, CampaignDonationDetailActivity::class.java).apply{
                        putExtra("donationId",donor.did)
                    }
                    context.startActivity(intent)
                }
            })
        }

    }

}