package com.example.harrisonwjy.charitree.helper
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import com.example.harrisonwjy.charitree.R
import com.example.harrisonwjy.charitree.model.CampaignItems
import kotlinx.android.synthetic.main.item_choose_quantity.view.*


class ChooseQuantityAdapter (private val campaignItems: ArrayList<CampaignItems>, private val onItemCheckListener: OnItemCheckListener) : RecyclerView.Adapter<ChooseQuantityAdapter.ItemCheckbox>() {


    //private val listener: OnItemCheckListener? = onItemCheckListener

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ChooseQuantityAdapter.ItemCheckbox {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.item_choose_quantity,p0, false)
        return ItemCheckbox(v)
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
       return campaignItems.size
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(p0: ChooseQuantityAdapter.ItemCheckbox, p1: Int) {
        p0.bindItems(campaignItems[p1],onItemCheckListener)

    }

    //the class is hodling the list view
    class ItemCheckbox(v: View) : RecyclerView.ViewHolder(v) {

        private var view: View = v
        fun bindItems(campaignItems: CampaignItems, onItemCheckListener: OnItemCheckListener) {
            view.itemTextView.text = campaignItems.item_name

            view.quantityOneCheckBox.setOnClickListener(TickOneOnly(view,campaignItems,onItemCheckListener))
            view.quantityTwoCheckBox.setOnClickListener(TickOneOnly(view,campaignItems,onItemCheckListener))
            view.quantityThreeCheckBox.setOnClickListener(TickOneOnly(view,campaignItems,onItemCheckListener))
        }

        companion object {
            //5
            private val PHOTO_KEY = "PHOTO"
        }
    }
}

class TickOneOnly constructor(val view1: View,val campaignItems: CampaignItems,val onItemCheckListener: OnItemCheckListener) : View.OnClickListener{
    override fun onClick(view: View?) {
        val oneCheckBox = view1!!.findViewById<CheckBox>(R.id.quantityOneCheckBox)
        val twoCheckBox = view1!!.findViewById<CheckBox>(R.id.quantityTwoCheckBox)
        val threeCheckBox = view1!!.findViewById<CheckBox>(R.id.quantityThreeCheckBox)
        when (view!!.id) {
            R.id.quantityOneCheckBox -> {
                twoCheckBox.isChecked = false
                threeCheckBox.isChecked = false
                if(!oneCheckBox.isChecked){
                    // if quantityOneCheckBox is unchecked - rest is unchecked too
                    campaignItems.quantity = 0
                    onItemCheckListener.onItemUncheck(campaignItems)
                }else{
                    campaignItems.quantity = 1
                    onItemCheckListener.onItemCheck(campaignItems)
                }
            }
            R.id.quantityTwoCheckBox -> {
                oneCheckBox.isChecked = false
                threeCheckBox.isChecked = false
                if(!twoCheckBox.isChecked){
                    // if quantityOneCheckBox is unchecked - rest is unchecked too
                    campaignItems.quantity = 0
                    onItemCheckListener.onItemUncheck(campaignItems)
                }else{
                    campaignItems.quantity = 2
                    onItemCheckListener.onItemCheck(campaignItems)
                }
            }
            R.id.quantityThreeCheckBox -> {
                oneCheckBox.isChecked = false
                twoCheckBox.isChecked = false
                if(!threeCheckBox.isChecked){
                    // if quantityOneCheckBox is unchecked - rest is unchecked too
                    campaignItems.quantity = 0
                    onItemCheckListener.onItemUncheck(campaignItems)
                }else{
                    campaignItems.quantity = 3
                    onItemCheckListener.onItemCheck(campaignItems)
                }
            }
        }
    }

}


