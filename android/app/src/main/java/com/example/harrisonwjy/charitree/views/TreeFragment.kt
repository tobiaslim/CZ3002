package com.example.harrisonwjy.charitree.views

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.harrisonwjy.charitree.R
import com.example.harrisonwjy.charitree.repo.CampaignRepo
import com.example.harrisonwjy.charitree.repo.DonationRepo
import com.example.harrisonwjy.charitree.viewmodel.DonationViewModel
import kotlinx.android.synthetic.main.fragment_tree.*
import org.koin.android.viewmodel.ext.android.viewModel

// TODO: Rename parameter arguments, choose names that match

/**
 * A TreeFragment holds the UI of the Tree Screen
 * @author Harrison Wong
 *
 */
class TreeFragment : Fragment() {

    private val donationViewModel : DonationViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tree, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val prefs = getActivity()!!.getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE)
        val token: String = prefs.getString("token", "")//"No name defined" is the default value.
        val email: String = prefs.getString("email","")

        donationViewModel.getDonationCount(DonationRepo(email,token),"Completed").observe(this,android.arch.lifecycle.Observer{
            if(it?.status == 1){
                donationCountTextView.text = "Your donation count is at "+ it.count.toString()
            }
        })
    }

    companion object {
        fun newInstance(): TreeFragment {
            return TreeFragment()
        }

    }
}
