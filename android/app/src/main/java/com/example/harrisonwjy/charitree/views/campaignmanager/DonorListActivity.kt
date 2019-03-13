package com.example.harrisonwjy.charitree.views.campaignmanager

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.example.harrisonwjy.charitree.R
import com.example.harrisonwjy.charitree.helper.DonorListAdapter
import com.example.harrisonwjy.charitree.model.Donation
import com.example.harrisonwjy.charitree.repo.CampaignRepo
import com.example.harrisonwjy.charitree.viewmodel.CampaignViewModel
import kotlinx.android.synthetic.main.activity_donor_list.*
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * DonorListActivity class holds a recycleview of donors for the specific campaign
 * @author Wang Lu
 */
class DonorListActivity : AppCompatActivity() {

    private val campaignViewModel : CampaignViewModel by viewModel()
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<DonorListAdapter.ViewHolder>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donor_list)

        val campaignId = intent.getIntExtra("campaignId",0)

        val recycler_view = findViewById<RecyclerView>(R.id.recycler_view)

        layoutManager = LinearLayoutManager(this)
        recycler_view.layoutManager = layoutManager

        // get token
        val prefs = getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE)
        val token: String = prefs.getString("token", "")//"No name defined" is the default value.
        val email: String = prefs.getString("email","")


        campaignViewModel.getListOfDonors(CampaignRepo(email,token),campaignId!!).observe(this,android.arch.lifecycle.Observer{
            if(it?.status == 1){
                val donors : ArrayList<Donation>? = it.donations
                adapter = DonorListAdapter(donors)
                recycler_view.adapter = adapter
                emptyState.visibility = View.INVISIBLE
            }
        })


    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        if(hasFocus){
            val campaignId = intent.getIntExtra("campaignId",0)
            Log.e("DonorlistActivity","Page has refresh with updated data")
            val prefs = getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE)
            val token: String = prefs.getString("token", "")//"No name defined" is the default value.
            val email: String = prefs.getString("email","")
            campaignViewModel.getListOfDonors(CampaignRepo(email,token),campaignId!!).observe(this,android.arch.lifecycle.Observer{
                if(it?.status == 1){
                    val donors : ArrayList<Donation>? = it.donations
                    adapter = DonorListAdapter(donors)
                    recycler_view.adapter = adapter
                    emptyState.visibility = View.INVISIBLE
                }
            })
        }
    }




}
