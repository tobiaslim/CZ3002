package com.example.harrisonwjy.charitree.helper

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.example.harrisonwjy.charitree.model.DateItem
import com.example.harrisonwjy.charitree.views.createdonation.DateItemFragment

class DateItemAdapter(fragmentManager: FragmentManager) : SmartFragmentStatePagerAdapter(fragmentManager) {
    var data: Array<String>? = null
    var dates: ArrayList<DateItem>? = null

//    constructor(fragmentManager: FragmentManager, data2: Array<String>) : this(fragmentManager){
//        data = data2
//    }

    constructor(fragmentManager: FragmentManager, data2: ArrayList<DateItem>?) : this(fragmentManager){
        dates = data2
    }


    // 2
    override fun getItem(position: Int): Fragment {
        //return MovieFragment.newInstance(movies[position])
        val fragment = DateItemFragment()
        val args = Bundle()
        //args.putString("message", "Hello this is data  "+campaigns!!.get(position).name)
        //val campaign = campaigns!!.get(position)
        args.putInt("day", dates!!.get(position).day!!)
        args.putString("month",dates!!.get(position).month)
        fragment.setArguments(args)
        return fragment
    }

    // 3
    override fun getCount(): Int {
        return dates!!.size
    }

    override fun getItemPosition(`object`: Any): Int {
        return POSITION_NONE
    }

    override fun getPageWidth(position: Int): Float {
        return 0.93f
    }
}