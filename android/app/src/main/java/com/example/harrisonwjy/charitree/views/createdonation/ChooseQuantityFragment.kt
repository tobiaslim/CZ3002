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
import com.example.harrisonwjy.charitree.model.CampaignItems
import android.support.v7.widget.DividerItemDecoration
import android.widget.Button
import com.example.harrisonwjy.charitree.helper.ChooseQuantityAdapter
import com.example.harrisonwjy.charitree.helper.OnItemCheckListener
import com.example.harrisonwjy.charitree.model.Campaign
import kotlinx.android.synthetic.main.fragment_create_donation_quantity.*


/**
 * A ChooseQuantityFragment holds the UI for selecting the quantity (plastic bag, carton box or trolley) for the seleted items he/she wants to donate
 * The ChooseQuantityFragment has a RecycleView of three checkboxes that can be selected one only
 * @author Harrison Wong
 */
class ChooseQuantityFragment : Fragment() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private var campaignData: Campaign? = null
    private var selectedItems: ArrayList<CampaignItems> = ArrayList()
    private lateinit var mAdapter: ChooseQuantityAdapter
    private var savedState: Bundle? = null



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Log.e("CIQ","CIQ opened");
        val view =  inflater.inflate(R.layout.fragment_create_donation_quantity, container, false)

        val nextButton = view.findViewById<Button>(R.id.nextButton)
        val items = loadData()

        // set Campaign data
        campaignData = arguments?.getSerializable("campaign") as Campaign

        // setting up recycler view
        var selectionList = view.findViewById<RecyclerView>(R.id.selection_list)
        linearLayoutManager = LinearLayoutManager(context,LinearLayout.VERTICAL,false)
        val dividerItemDecoration = DividerItemDecoration(selectionList.getContext(), linearLayoutManager.getOrientation())
        selectionList.layoutManager = linearLayoutManager
        mAdapter = ChooseQuantityAdapter(selectedItems, object: OnItemCheckListener{
            override fun onItemCheck(item: CampaignItems) {
                Log.e("CQF","added item" + item.item_name +" with quantity of"+item.quantity)
                for(itemData in selectedItems){
                    if(itemData.choiceNumber == item.choiceNumber){
                        Log.e("key key ","key key is "+itemData.key)
                        itemData.quantity = item.quantity // set quantity to current data set
                    }
                }

                displayNextWhenFilled()
            }

            override fun onItemUncheck(item: CampaignItems) {
                for(itemData in selectedItems){
                    if(itemData.choiceNumber == item.choiceNumber){
                        itemData.quantity = item.quantity // set quantity to current data set
                    }
                }

                displayNextWhenFilled()

            }

        })
        selectionList.adapter = mAdapter
        selectionList.addItemDecoration(dividerItemDecoration)

        val size = mAdapter.itemCount
        selectedItems.addAll(items)
        mAdapter.notifyItemRangeInserted(size,items.size)

        nextButton.setOnClickListener {

            val fragment = ChooseDateAndTimeFragment()
            val args = Bundle()
//            Log.e("CIF","item size is "+selectedItems.size)
            args.putSerializable("currentChoices",selectedItems)
            args.putSerializable("campaign",campaignData)
            fragment.arguments = args

            val fragmentManager = fragmentManager
            val fragmentTransaction = fragmentManager!!.beginTransaction()
            fragmentTransaction.replace(R.id.frame_layout, fragment)
                    .addToBackStack("ChooseDateAndTimeFragment")
            fragmentTransaction.commit()
        }

        return view
    }

    private fun loadData(): ArrayList<CampaignItems>{
        @Suppress("UNCHECKED_Cast")
        val campaignItems = arguments?.getSerializable("currentChoices") as ArrayList<CampaignItems>

        var checkedItem = ArrayList<CampaignItems>()

        if(savedState != null) {
            val selected = savedState!!.getSerializable("selectedItems") as ArrayList<CampaignItems>
            for (item in selected){
                Log.e("CQF","Saved state: " +item.item_name +" with "+item.quantity)
            }
        }else{
            for (item in campaignItems){
                checkedItem.add(CampaignItems().apply{
                    key = item.key
                    item_name = item.item_name
                    choiceNumber = item.choiceNumber
                })
            }
        }

        return checkedItem
    }

    private fun displayNextWhenFilled(){
        var count: Int = 0
        for(itemData in selectedItems){
            if (itemData.quantity != 0){
                count++
            }
        }
        if(count == selectedItems.size){
            nextButton.visibility = View.VISIBLE
            noItemSelected.visibility = View.INVISIBLE
        }else{
            nextButton.visibility = View.INVISIBLE
            noItemSelected.visibility = View.VISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        savedState = saveState() /* vstup defined here for sure */


    }

    private fun saveState(): Bundle { /* called either from onDestroyView() or onSaveInstanceState() */
        val state = Bundle()
        state.putSerializable("selectedItems",selectedItems)
        return state
    }


    companion object {
        fun newInstance(): ChooseQuantityFragment {
            return ChooseQuantityFragment()
        }
    }
}


