package com.example.harrisonwjy.charitree.views.campaignmanager.createcampaign

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.harrisonwjy.charitree.R

/**
 * CreateCampaignActivity class holds the CampaignInformationFragment
 * @author Lim Yiern
 */
class CreateCampaignActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView()
        setContentView(R.layout.activity_create_campaign)


        val fragment = CampaignInformationFragment.newInstance()
        supportFragmentManager.beginTransaction()
                .add(R.id.frame_layout, fragment)
                .commit()
    }

}
