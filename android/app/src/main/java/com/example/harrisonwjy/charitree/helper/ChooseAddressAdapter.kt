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
import com.example.harrisonwjy.charitree.model.TimeSlot
import kotlinx.android.synthetic.main.item_choose_date_time.view.*
import kotlinx.android.synthetic.main.item_choose_quantity.view.*

interface OnItemCheckAddressListener {
    fun onItemCheck(item: Address)
    fun onItemUncheck(item: Address)
}
class ChooseAddressAdapter (private val addresses: ArrayList<Address>, private val onItemCheckAddressListener: OnItemCheckAddressListener) : RecyclerView.Adapter<ChooseAddressAdapter.ItemCheckbox>() {


    private var prevSelection = -1
    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ChooseAddressAdapter.ItemCheckbox {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.item_choose_date_time,p0, false)
        return ItemCheckbox(v)
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
       return addresses.size
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(p0: ChooseAddressAdapter.ItemCheckbox, p1: Int) {
        p0.bindItems(addresses[p1],onItemCheckAddressListener)
    }

    //the class is hodling the list view
    class ItemCheckbox(v: View) : RecyclerView.ViewHolder(v) {

        private var view: View = v
        fun bindItems(address: Address, onItemCheckAddressListener: OnItemCheckAddressListener) {
            view.timeCheckBox.text = address.street_name +" "+address.unit+" "+address.zip
            view.timeCheckBox.setOnClickListener{
                if(!view.timeCheckBox.isChecked){
                    view.timeCheckBox.isChecked = false
                    onItemCheckAddressListener.onItemUncheck(address)
                }else{
                    view.timeCheckBox.isChecked = true
                    onItemCheckAddressListener.onItemCheck(address)
                }
            }
            view.setOnClickListener {
                if(!view.timeCheckBox.isChecked){
                    view.timeCheckBox.isChecked = true
                    onItemCheckAddressListener.onItemCheck(address)

                }else{
                    view.timeCheckBox.isChecked = false
                    onItemCheckAddressListener.onItemUncheck(address)
                }
            }

        }
        companion object {
            private val PHOTO_KEY = "asd"
        }
    }
}



