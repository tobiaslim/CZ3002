package com.example.harrisonwjy.charitree.helper
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import com.example.harrisonwjy.charitree.R
import com.example.harrisonwjy.charitree.model.CampaignItems
import com.example.harrisonwjy.charitree.model.TimeSlot
import kotlinx.android.synthetic.main.item_choose_date_time.view.*
import kotlinx.android.synthetic.main.item_choose_quantity.view.*

interface OnItemCheckTimeListener {
    fun onItemCheck(item: TimeSlot)
    fun onItemUncheck(item: TimeSlot)
}
class ChooseDateAndTimeAdapter (private val timeSlots: ArrayList<TimeSlot>, private val onItemCheckTimeListener: OnItemCheckTimeListener) : RecyclerView.Adapter<ChooseDateAndTimeAdapter.ItemCheckbox>() {


    //private val listener: OnItemCheckListener? = onItemCheckListener

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ChooseDateAndTimeAdapter.ItemCheckbox {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.item_choose_date_time,p0, false)
        return ItemCheckbox(v)
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
       return timeSlots.size
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(p0: ChooseDateAndTimeAdapter.ItemCheckbox, p1: Int) {
        p0.bindItems(timeSlots[p1],onItemCheckTimeListener)

    }

    //the class is hodling the list view
    class ItemCheckbox(v: View) : RecyclerView.ViewHolder(v) {

        private var view: View = v
        fun bindItems(timeSlot: TimeSlot, onItemCheckTimeListener: OnItemCheckTimeListener) {
            view.timeCheckBox.text = timeSlot.start_time.toString() + "00 - "+timeSlot.end_time.toString()+"00"
            view.timeCheckBox.setOnClickListener{
                if(!view.timeCheckBox.isChecked){
                    view.timeCheckBox.isChecked = false
                    onItemCheckTimeListener.onItemUncheck(timeSlot)
                }else{
                    view.timeCheckBox.isChecked = true
                    onItemCheckTimeListener.onItemCheck(timeSlot)
                }
            }
            view.setOnClickListener {
                if(!view.timeCheckBox.isChecked){
                    view.timeCheckBox.isChecked = true
                    onItemCheckTimeListener.onItemCheck(timeSlot)

                }else{
                    view.timeCheckBox.isChecked = false
                    onItemCheckTimeListener.onItemUncheck(timeSlot)
                }
            }

//            view.quantityOneCheckBox.setOnClickListener(TickOneOnly(view,campaignItems,onItemCheckListener))
//            view.quantityTwoCheckBox.setOnClickListener(TickOneOnly(view,campaignItems,onItemCheckListener))
//            view.quantityThreeCheckBox.setOnClickListener(TickOneOnly(view,campaignItems,onItemCheckListener))
        }

        companion object {
            //5
            private val PHOTO_KEY = "asd"
        }
    }
}



