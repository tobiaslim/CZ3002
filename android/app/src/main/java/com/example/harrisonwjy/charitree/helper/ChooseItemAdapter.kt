package com.example.harrisonwjy.charitree.helper

import android.content.Context
import android.widget.TextView
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.CheckedTextView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.harrisonwjy.charitree.R
import com.example.harrisonwjy.charitree.model.CampaignItems
import kotlinx.android.synthetic.main.item_choose_item.view.*
import android.content.ClipData.Item
import android.support.annotation.NonNull
import android.widget.AdapterView.OnItemClickListener










interface OnItemCheckListener {
    fun onItemCheck(item: CampaignItems)
    fun onItemUncheck(item: CampaignItems)
}

class ChooseItemAdapter(private val campaignItems: ArrayList<CampaignItems>, private val onItemCheckListener: OnItemCheckListener) : RecyclerView.Adapter<ChooseItemAdapter.ItemCheckbox>() {


    //private val listener: OnItemCheckListener? = onItemCheckListener



    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ChooseItemAdapter.ItemCheckbox {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.item_choose_item,p0, false)
        return ItemCheckbox(v)
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
       return campaignItems.size
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(p0: ChooseItemAdapter.ItemCheckbox, p1: Int) {
        p0.bindItems(campaignItems[p1],onItemCheckListener)

    }

    //the class is hodling the list view
    class ItemCheckbox(v: View) : RecyclerView.ViewHolder(v){


        private var view: View = v
        fun bindItems(campaignItems: CampaignItems,onItemCheckListener: OnItemCheckListener) {
            view.contact_name.text = campaignItems.item_name
            view.contact_name.isChecked = campaignItems.checked!!
            view.contact_name.setOnClickListener{
                if(!view.contact_name.isChecked){
                    view.contact_name.isChecked = false
                    onItemCheckListener.onItemUncheck(campaignItems)
                }else{
                    view.contact_name.isChecked = true
                    onItemCheckListener.onItemCheck(campaignItems)
                }
            }
            view.setOnClickListener {
                if(!view.contact_name.isChecked){
                    view.contact_name.isChecked = true
                    onItemCheckListener.onItemCheck(campaignItems)

                }else{
                    view.contact_name.isChecked = false
                    onItemCheckListener.onItemUncheck(campaignItems)
                }
            }
        }

        companion object {
            //5
            private val PHOTO_KEY = "PHOTO"
        }
    }
}


