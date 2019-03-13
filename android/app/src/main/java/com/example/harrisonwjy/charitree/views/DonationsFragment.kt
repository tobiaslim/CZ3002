package com.example.harrisonwjy.charitree.views

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.harrisonwjy.charitree.R
import com.example.harrisonwjy.charitree.helper.MyDonationAdapter
import com.example.harrisonwjy.charitree.model.Donation
import com.example.harrisonwjy.charitree.repo.CampaignRepo
import com.example.harrisonwjy.charitree.repo.DonationRepo
import com.example.harrisonwjy.charitree.views.createdonation.IOnFocusListenable
import com.example.harrisonwjy.charitree.viewmodel.DonationViewModel
import kotlinx.android.synthetic.main.fragment_donations.*
import org.koin.android.viewmodel.ext.android.viewModel

// TODO: Rename parameter arguments, choose names that match

/**
 * A DonationsFragment holds the UI of the Donation Screen
 * @author Harrison Wong
 *
 */
class DonationsFragment : Fragment(), IOnFocusListenable {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_donations, container, false)
    }

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<MyDonationAdapter.ViewHolder>? = null
    private val donationViewModel : DonationViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        layoutManager = LinearLayoutManager(context)
        recycler_view.layoutManager = layoutManager

        val prefs = getActivity()!!.getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE)
        val token: String = prefs.getString("token", "")//"No name defined" is the default value.
        val email: String = prefs.getString("email","")

        donationViewModel.getAllDonationForUser(DonationRepo(email,token)).observe(this,android.arch.lifecycle.Observer{
            if(it?.status == 1){
                val donations : ArrayList<Donation>? = it.donations
                val adapter = MyDonationAdapter(donations)
                recycler_view.adapter = adapter
                emptyState.visibility = View.INVISIBLE
            }
        })
    }

    companion object {
        fun newInstance(): DonationsFragment {
            return DonationsFragment()
        }

    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        if(hasFocus){
            Log.e("DonationsFragment","Page has refresh with updated data")
            val prefs = getActivity()!!.getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE)
            val token: String = prefs.getString("token", "")//"No name defined" is the default value.
            val email: String = prefs.getString("email","")
            donationViewModel.getAllDonationForUser(DonationRepo(email,token)).observe(this,android.arch.lifecycle.Observer{
                if(it?.status == 1){
                    val donations : ArrayList<Donation>? = it.donations
                    val adapter = MyDonationAdapter(donations)
                    recycler_view.adapter = adapter
                    emptyState.visibility = View.INVISIBLE
                }
            })
        }
    }
}
