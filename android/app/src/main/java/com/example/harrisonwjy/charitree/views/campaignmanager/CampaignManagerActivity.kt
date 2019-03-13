package com.example.harrisonwjy.charitree.views.campaignmanager

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar
import com.example.harrisonwjy.charitree.model.User

import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel
import android.support.v4.app.Fragment
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.example.harrisonwjy.charitree.R
import com.example.harrisonwjy.charitree.views.campaignmanager.createcampaign.CreateCampaignActivity
import com.example.harrisonwjy.charitree.helper.BottomBarAdapter
import com.example.harrisonwjy.charitree.helper.LockableViewPager
import com.example.harrisonwjy.charitree.views.setting.SettingFragment
import com.example.harrisonwjy.charitree.views.createdonation.IOnFocusListenable
import com.example.harrisonwjy.charitree.viewmodel.UserViewModel



fun Context.CampaignManagerActivity(user: User): Intent {
    return Intent(this, CampaignManagerActivity::class.java).apply {
        putExtra(INTENT_USER_ID, user.user_token)
    }
}

private val INTENT_USER_ID = "user_token"
private lateinit var mAdapter: BottomBarAdapter

/**
 * CampaignManagerActivity holds the the Main Activity of the Campaign Mananger Mode
 * When User switch to Campaign Manager mode, it will be redirected to this Activity
 * This class consist of two class such as CreatedCampaignsFragment and SettingFragment
 * @author Harrison Wong
 */
class CampaignManagerActivity : AppCompatActivity() {

    private lateinit var viewPager: LockableViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_campaign_manager)
        //setSupportActionBar(toolbar)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)

        val navigation = navigationView

        viewPager = findViewById(R.id.viewPager)
//
        setSupportActionBar(toolbar)

        mAdapter = BottomBarAdapter(supportFragmentManager)
        mAdapter.addFragment(CreatedCampaignsFragment())
        mAdapter.addFragment(SettingFragment())
        viewPager.adapter = mAdapter

        viewPager.setSwipeable(false)

        navigation.setOnNavigationItemSelectedListener(test)

    }


    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        Log.e("CampaignManagerActivity","Gained focus "+hasFocus +" "+ viewPager.currentItem)
        if(viewPager.currentItem == 0) {
            //if (currentFragment is IOnFocusListenable) {
            val fragment = mAdapter.getItem(0)
            (fragment as IOnFocusListenable).onWindowFocusChanged(hasFocus)
            //}
        }

    }
    private val test = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        var selectedFragment: Fragment? = null
        var fragmentTag: String? = null
        when (item.itemId) {
            R.id.campaign -> {
                toolbar.title = "Campaigns"
                viewPager.setCurrentItem(0)
                selectedFragment = CreatedCampaignsFragment.newInstance()

            }
            R.id.setting -> {
                toolbar.title = "Setting"
                viewPager.setCurrentItem(1)
                selectedFragment = SettingFragment.newInstance()
            }
        }
        true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.setting_menu, menu)
        return true
    }

    //and this to handle actions
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.getItemId()
        return if (id == R.id.action_settings) {
            val intent = Intent(this, CreateCampaignActivity::class.java).apply{
//                putExtra("campaign",campaign)
            }
            startActivity(intent)

            true
        } else super.onOptionsItemSelected(item)
    }
}
