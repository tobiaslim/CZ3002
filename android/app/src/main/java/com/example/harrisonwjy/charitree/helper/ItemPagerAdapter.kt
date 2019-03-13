package com.example.harrisonwjy.charitree.helper

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.example.harrisonwjy.charitree.model.Campaign
import com.example.harrisonwjy.charitree.views.CampaignItemFragment

class ItemPagerAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {

    var data: Array<String>? = null
    var campaigns: ArrayList<Campaign>? = null

//    constructor(fragmentManager: FragmentManager, data2: Array<String>) : this(fragmentManager){
//        data = data2
//    }

    constructor(fragmentManager: FragmentManager, data2: ArrayList<Campaign>?) : this(fragmentManager){
        campaigns = data2
    }


    // 2
    override fun getItem(position: Int): Fragment {
        //return MovieFragment.newInstance(movies[position])
        val fragment = CampaignItemFragment()
        val args = Bundle()
        //args.putString("message", "Hello this is data  "+campaigns!!.get(position).name)
        val campaign = campaigns!!.get(position)
        args.putSerializable("campaign",campaign)
        fragment.setArguments(args)
        return fragment
    }

    // 3
    override fun getCount(): Int {
        return campaigns!!.size
    }

    override fun getItemPosition(`object`: Any): Int {
        return POSITION_NONE
    }


}