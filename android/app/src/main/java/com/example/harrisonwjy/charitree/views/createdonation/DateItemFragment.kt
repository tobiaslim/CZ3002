package com.example.harrisonwjy.charitree.views.createdonation

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.harrisonwjy.charitree.R

/**
 * A DateItemFragment is used during the creation of donation
 * The DateItemFragment will be added in a viewpager and acts a date picker
 * @author Harrison Wong
 */
class DateItemFragment: Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_date_item, container, false)

        val day = arguments?.getInt("day")
        val month = arguments?.getString("month")

        val dayTextView = view.findViewById<TextView>(R.id.dayTextView)
        val monthTextView = view.findViewById<TextView>(R.id.monthTextView)
        dayTextView.text = day.toString()
        monthTextView.text = month.toString()

        return view
    }
}