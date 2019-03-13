package com.example.harrisonwjy.charitree.views.createdonation

import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.harrisonwjy.charitree.R
import com.example.harrisonwjy.charitree.helper.ChooseItemAdapter
import com.example.harrisonwjy.charitree.model.CampaignItems
import kotlinx.android.synthetic.main.fragment_create_donation_items.*
import android.support.v7.widget.DividerItemDecoration
import com.example.harrisonwjy.charitree.helper.OnItemCheckListener
import android.widget.Button
import com.example.harrisonwjy.charitree.model.AcceptedItem
import com.example.harrisonwjy.charitree.model.Campaign


/**
 * A ChooseItemsFragment holds the UI of choosing the items they (or donors) want to donate
 * @author Harrison Wong
 */
class ChooseItemsFragment : Fragment() {


    private lateinit var linearLayoutManager: LinearLayoutManager
    private var campaignData: Campaign? = null
    private var data = ArrayList<CampaignItems>()
    //val currentItem = ArrayList<Int>()
    private var savedState: Bundle? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view =  inflater.inflate(R.layout.fragment_create_donation_items, container, false)

        //var data = ArrayList<CampaignItems>()
        var selectionList = view.findViewById<RecyclerView>(R.id.selection_list)

        // set Campaign data
        campaignData = arguments?.getSerializable("campaign") as Campaign

        val nextButton = view.findViewById<Button>(R.id.nextButton)
        // when retrieve old data
        if(savedState != null) {
            nextButton.visibility = View.VISIBLE

            val selected = savedState!!.getSerializable("datadata") as ArrayList<CampaignItems>
            for (item in data){
                for (item2 in selected){
                    if (item.choiceNumber!!.equals(item2)){
                        item.checked = true
                        break;
                    }
                }
            }

        }else{
            // if no data found

            // load data
            for (item in campaignData!!.accepted_items as java.util.ArrayList<AcceptedItem>){
                data.add(CampaignItems().apply{
                    Log.e("keykey","key is "+item.key)
                    key = item.key
                    item_name = item.value
                    choiceNumber = item.key
                    checked = false
                })
            }
        }

        linearLayoutManager = LinearLayoutManager(context,LinearLayout.VERTICAL,false)
        val dividerItemDecoration = DividerItemDecoration(selectionList.getContext(), linearLayoutManager.getOrientation())
        selectionList.layoutManager = linearLayoutManager

        selectionList.adapter = ChooseItemAdapter(data, object: OnItemCheckListener{
            override fun onItemCheck(item: CampaignItems) {
                for(itemData in data){
                    if(itemData.choiceNumber == item.choiceNumber){
                        itemData.checked = true
                    }
                }

                displayWhenOneTick()
            }

            override fun onItemUncheck(item: CampaignItems) {
                for(itemData in data){
                    if(itemData.choiceNumber == item.choiceNumber){
                        itemData.checked = false
                    }
                }
                displayWhenOneTick()
            }

        })
        selectionList.addItemDecoration(dividerItemDecoration)

        nextButton.setOnClickListener {

            var selectedItems : ArrayList<CampaignItems> = ArrayList()

            // only pass selected items
            data.forEachIndexed { index, campaignItems ->
                if(campaignItems.checked == true){
                    selectedItems.add(campaignItems)
                }
            }

            val fragment = ChooseQuantityFragment()
            val args = Bundle()
//            Log.e("CIF","item size is "+selectedItems.size)
            args.putSerializable("currentChoices",selectedItems)
            args.putSerializable("campaign",campaignData)
            fragment.arguments = args

            val fragmentManager = fragmentManager
            val fragmentTransaction = fragmentManager!!.beginTransaction()
            fragmentTransaction.replace(R.id.frame_layout, fragment)
                    .addToBackStack("ChooseItemsFragment")
            fragmentTransaction.commit()
        }

        return view
    }


    override fun onDestroyView() {
        super.onDestroyView()
        savedState = saveState() /* vstup defined here for sure */
    }

    private fun saveState(): Bundle { /* called either from onDestroyView() or onSaveInstanceState() */
        val state = Bundle()
        //state.putIntegerArrayList("currentChoices",currentItem)
        state.putSerializable("datadata",data)
        return state
    }

    private fun displayWhenOneTick(){
        nextButton.visibility = View.INVISIBLE
        noItemSelected.visibility = View.VISIBLE
        for (itemData in data){
            if(itemData.checked == true){
                nextButton.visibility = View.VISIBLE
                noItemSelected.visibility = View.INVISIBLE
                break;
            }
        }
    }
    companion object {
        fun newInstance(): ChooseItemsFragment {
            return ChooseItemsFragment()
        }

    }
}



