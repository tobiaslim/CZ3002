package com.example.harrisonwjy.charitree.helper
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import com.example.harrisonwjy.charitree.R
import com.example.harrisonwjy.charitree.model.Address
import com.example.harrisonwjy.charitree.model.CampaignItems
import com.example.harrisonwjy.charitree.model.Items
import com.example.harrisonwjy.charitree.model.TimeSlot
import kotlinx.android.synthetic.main.item_choose_date_time.view.*
import kotlinx.android.synthetic.main.item_choose_quantity.view.*

interface OnRequestItemCheckListener {
    fun onItemCheck(item: Items)
    fun onItemUncheck(item: Items)
}

class ChooseRequestedItemAdapter (private val items: ArrayList<Items>, private val onRequestItemCheckListener: OnRequestItemCheckListener) : RecyclerView.Adapter<ChooseRequestedItemAdapter.ItemCheckbox>() {


    private var prevSelection = -1
    //private val listener: OnItemCheckListener? = onItemCheckListener

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ChooseRequestedItemAdapter.ItemCheckbox {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.item_choose_date_time,p0, false)
        return ItemCheckbox(v)
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
       return items.size
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(p0: ChooseRequestedItemAdapter.ItemCheckbox, p1: Int) {
        p0.bindItems(items[p1],onRequestItemCheckListener)
    }

    //the class is hodling the list view
    class ItemCheckbox(v: View) : RecyclerView.ViewHolder(v) {

        private var view: View = v
        fun bindItems(item: Items, onRequestItemCheckListener: OnRequestItemCheckListener) {
            view.timeCheckBox.text = item.name

            view.timeCheckBox.setOnClickListener{
                if(!view.timeCheckBox.isChecked){
                    view.timeCheckBox.isChecked = false
                    onRequestItemCheckListener.onItemUncheck(item)
                }else{
                    view.timeCheckBox.isChecked = true
                    onRequestItemCheckListener.onItemCheck(item)
                }
            }
            view.setOnClickListener {
                if(!view.timeCheckBox.isChecked){
                    view.timeCheckBox.isChecked = true
                    onRequestItemCheckListener.onItemCheck(item)

                }else{
                    view.timeCheckBox.isChecked = false
                    onRequestItemCheckListener.onItemUncheck(item)
                }
            }

        }
        companion object {
            private val PHOTO_KEY = "asd"
        }
    }
}



