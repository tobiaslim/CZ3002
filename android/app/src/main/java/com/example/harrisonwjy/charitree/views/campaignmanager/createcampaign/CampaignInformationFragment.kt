package com.example.harrisonwjy.charitree.views.campaignmanager.createcampaign

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

import com.example.harrisonwjy.charitree.R
import kotlinx.android.synthetic.main.fragment_campaign_information.*


/**
 * A CampaignInformationFragment holds a UI of creation of campaign (1st part)
 * The CampaignInformationFragment consist of four text inputs (campaign name, description, street name, postal code) and a button to process
 * to the next fragment
 *  @author Lim Yiern
 */
class CampaignInformationFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_campaign_information, container, false)

        val nextButton = view.findViewById<Button>(R.id.nextButton)

        nextButton.setOnClickListener {
            val campaignName = input_campaign_name.text
            val description = input_description.text
            val postalCode = input_postal_code.text
            var streetName = input_street_name.text

            Log.e("CamInfoFrag","campaignName: "+ campaignName)

            val bundle = Bundle()
            bundle.apply{
                putString("campaignName",campaignName.toString())
                putString("description",description.toString())
                putString("postalCode",postalCode.toString())
                putString("streetName",streetName.toString())
            }

            val fragment = CampaignDateAndTimeFragment()
            fragment.arguments = bundle
            val fragmentManager = fragmentManager
            val fragmentTransaction = fragmentManager!!.beginTransaction()
            fragmentTransaction.replace(R.id.frame_layout, fragment)
                    .addToBackStack("CampaignDateAndTimeFragment")
            fragmentTransaction.commit()
        }

        return view

    }


    companion object {
        fun newInstance(): CampaignInformationFragment {
            return CampaignInformationFragment()
        }
    }

}
