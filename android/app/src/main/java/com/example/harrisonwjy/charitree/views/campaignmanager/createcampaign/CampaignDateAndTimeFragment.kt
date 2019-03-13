package com.example.harrisonwjy.charitree.views.campaignmanager.createcampaign

import android.app.DatePickerDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView

import com.example.harrisonwjy.charitree.R
import com.example.harrisonwjy.charitree.model.Campaign
import com.example.harrisonwjy.charitree.model.CampaignItems
import kotlinx.android.synthetic.main.fragment_campaign_date_and_time.*
import java.util.*


/**
 * A CampaignDateAndTimeFragment holds the UI of creation of campaign (2nd part)
 * The CampaignDateAndTimeFragment consist of four text input (two of them has a date picker) - Start date, end date, start time, end time
 * and a button to submit the request
 *  @author Lim Yiern
 */
class CampaignDateAndTimeFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    private lateinit var mDisplayDate: TextView
    private lateinit var mDateSetListener: DatePickerDialog.OnDateSetListener
    private lateinit var nDisplayDate: TextView
    private lateinit var nDateSetListener: DatePickerDialog.OnDateSetListener

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_campaign_date_and_time, container, false)


        mDisplayDate = view.findViewById(R.id.input_start_date)
        mDisplayDate.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View): Unit {
                var cal = Calendar.getInstance()
                var year = cal.get(Calendar.YEAR)
                var month = cal.get(Calendar.MONTH)
                var day = cal.get(Calendar.DAY_OF_MONTH)
                var dialog = DatePickerDialog(
                        context,
                        android.R.style.ThemeOverlay_Material_Dialog, mDateSetListener,
                        year, month, day
                )
                dialog.window.setBackgroundDrawable(ColorDrawable(Color.WHITE))
                dialog.show()
            }

        })
        mDateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(datePicker: DatePicker, year: Int, month: Int, day: Int) {
                var month = month + 1
                /*Log.d(TAG, "onDateSet: dd/mm/yyyy: " + day + "/" + month + "/" + year)*/
                val date: String = day.toString() + "/" + month.toString() + "/" + year.toString()
                mDisplayDate.setText(date)
            }

        }

        nDisplayDate = view.findViewById(R.id.input_end_date)
        nDisplayDate.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                var cal = Calendar.getInstance()
                var year = cal.get(Calendar.YEAR)
                var month = cal.get(Calendar.MONTH)
                var day = cal.get(Calendar.DAY_OF_MONTH)
                var dialog = DatePickerDialog(
                        context,
                        android.R.style.ThemeOverlay_Material_Dialog, nDateSetListener,
                        year, month, day
                )
                dialog.window.setBackgroundDrawable(ColorDrawable(Color.WHITE))
                dialog.show()
            }
        })
        nDateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(datePicker: DatePicker, year: Int, month: Int, day: Int) {
                var month = month + 1
                val date: String = day.toString() + "/" + month.toString() + "/" + year.toString()
                nDisplayDate.setText(date)
            }
        }


        val nextButton = view.findViewById<Button>(R.id.nextButton)

        nextButton.setOnClickListener {

            val startDate = input_start_date.text
            val endDate = input_end_date.text
            val startTime = input_start_time.text
            val endTime = input_end_time.text

            val bundle = Bundle()
            bundle.apply{
                putString("campaignName",arguments?.getString("campaignName"))
                putString("description",arguments?.getString("description"))
                putString("postalCode",arguments?.getString("postalCode"))
                putString("streetName",arguments?.getString("streetName"))
                putString("startDate",startDate.toString())
                putString("endDate",endDate.toString())
                putString("startTime",startTime.toString())
                putString("endTime",endTime.toString())
            }

            val fragment = CampaignItemsFragment()
            fragment.arguments = bundle
            val fragmentManager = fragmentManager
            val fragmentTransaction = fragmentManager!!.beginTransaction()
            fragmentTransaction.replace(R.id.frame_layout, fragment)
                    .addToBackStack("CampaignItemsFragment")
            fragmentTransaction.commit()
        }
        return view
    }

    companion object {
        fun newInstance(): CampaignDateAndTimeFragment {
            return CampaignDateAndTimeFragment()
        }
    }
}
