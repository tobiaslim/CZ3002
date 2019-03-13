package com.example.harrisonwjy.charitree.views.campaignmanager.createcampaign

import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

import com.example.harrisonwjy.charitree.R
import com.example.harrisonwjy.charitree.helper.ChooseRequestedItemAdapter
import com.example.harrisonwjy.charitree.helper.OnRequestItemCheckListener
import com.example.harrisonwjy.charitree.model.Items
import com.example.harrisonwjy.charitree.model.request.CreateCampaignRequest
import com.example.harrisonwjy.charitree.repo.CampaignRepo
import com.example.harrisonwjy.charitree.viewmodel.CampaignViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat


/**
 * A CampaignItemsFragment holds the UI for user to choose the items they requesting for
 * The CampaignItemsFragment has a recycleview with a checkbox in every row
 * @author Lim Yiern
 */
class CampaignItemsFragment : Fragment() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var selectionList: RecyclerView
    private lateinit var mAdapter: ChooseRequestedItemAdapter
    private var selectedRequestedItems : ArrayList<Items> = ArrayList()

    //private val campaignViewModel : CampaignViewModel by viewModel()
    private val campaignViewModel by sharedViewModel<CampaignViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_campaign_items, container, false)


        val nextButton = view.findViewById<Button>(R.id.nextButton)
        val noItemSelected = view.findViewById<TextView>(R.id.noItemSelected)

        // setting up recycler view
        selectionList = view.findViewById<RecyclerView>(R.id.selection_list)
        linearLayoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL,false)
        val dividerItemDecoration = DividerItemDecoration(selectionList.getContext(), linearLayoutManager.getOrientation())
        selectionList.layoutManager = linearLayoutManager
        selectionList.addItemDecoration(dividerItemDecoration)


        // put inside viewmodel
        val prefs = getActivity()!!.getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE)
        val token: String = prefs.getString("token", "")//"No name defined" is the default value.
        val email: String = prefs.getString("email","")


        campaignViewModel.getItems(CampaignRepo(email,token)).observe(this,android.arch.lifecycle.Observer{
            if(it?.status == 1){
                val addresses : ArrayList<Items>? = it.items
                mAdapter = ChooseRequestedItemAdapter(addresses!!, object: OnRequestItemCheckListener {
                    override fun onItemCheck(item: Items) {
                        selectedRequestedItems.add(item)

                        if(selectedRequestedItems.size > 0){
                            nextButton.visibility = View.VISIBLE
                            noItemSelected.visibility = View.INVISIBLE
                        }else{
                            nextButton.visibility = View.INVISIBLE
                            noItemSelected.visibility = View.VISIBLE
                        }
                    }

                    override fun onItemUncheck(item: Items) {
                        selectedRequestedItems.remove(item)

                        if(selectedRequestedItems.size > 0){
                            nextButton.visibility = View.VISIBLE
                            noItemSelected.visibility = View.INVISIBLE
                        }else{
                            nextButton.visibility = View.INVISIBLE
                            noItemSelected.visibility = View.VISIBLE
                        }
                    }
                })
                selectionList.adapter = mAdapter
            }
        })

        nextButton.setOnClickListener {

            nextButton.isEnabled = false
            val getCampaignName = arguments?.getString("campaignName")
            val getDescription = arguments?.getString("description")
            val getPostalCode = arguments?.getString("postalCode")
            val getStreetName = arguments?.getString("streetName")
            val getStartDate = arguments?.getString("startDate")
            val getEndDate = arguments?.getString("endDate")
            val getStartTime = arguments?.getString("startTime")
            val getEndTime = arguments?.getString("endTime")

            var correctStartDate : String? = null
            var correctEndDate: String? = null
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                val getStartDate = LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE)
//                val getEndDate = LocalDate.parse(endDate, DateTimeFormatter.ISO_DATE)

                val orgFormatter = SimpleDateFormat("dd/MM/yyyy")
                val newFormatter = SimpleDateFormat("yyyy-MM-dd")
                val getUpdatedStartDate = orgFormatter.parse(getStartDate)
                val getUpdatedEndDate = orgFormatter.parse(getEndDate)
                correctStartDate = newFormatter.format(getUpdatedStartDate)
                correctEndDate = newFormatter.format(getUpdatedEndDate)


            }else{
                correctStartDate = getStartDate
                correctEndDate = getEndDate
            }

            var correctStartTime = getStartTime!!.substring(0,getStartTime!!.length - 2)
            var correctEndTime = getEndTime!!.substring(0,getEndTime!!.length - 2)

            val request = CreateCampaignRequest().apply{
                name = getCampaignName
                description = getDescription
                postal_code = getPostalCode
                collection_point = getStreetName
                start_date = correctStartDate
                end_date = correctEndDate
                start_time = correctStartTime.toInt()
                end_time = correctEndTime.toInt()
                accepted_items.apply{
                    for (item in selectedRequestedItems){
                        add(item.id!!)
                    }
                }
            }

            campaignViewModel.createCampaign(CampaignRepo(email,token),request).observe(this,android.arch.lifecycle.Observer{
                if(it?.status == 1){
                    Log.e("CampaignItemsFragment","Campaign created successful")
                    nextButton.isEnabled = true
                    Toast.makeText(activity, "Your campaign has been created", Toast.LENGTH_LONG).show()
                    activity!!.finish()
                }
            })
            //Log.e("CampaignItemsFragment","CampaignName: "+campaignName +"description: "+ description +" postalcode: " +postalCode +" streetName: "+ streetName +" startDate: " +correctStartDate +" endDate: "+ correctEndDate +" startTime: "+ correctStartTmie +" endTime: "+correctEndTime)
        }

        return view
    }


    companion object {
        fun newInstance(): CampaignItemsFragment {
            return CampaignItemsFragment()
        }
    }

}
