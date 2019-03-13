package com.example.harrisonwjy.charitree.views

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar
import com.example.harrisonwjy.charitree.model.User

import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel
import android.support.v4.app.Fragment
import android.util.Log
import com.example.harrisonwjy.charitree.R
import com.example.harrisonwjy.charitree.helper.BottomBarAdapter
import com.example.harrisonwjy.charitree.helper.LockableViewPager
import com.example.harrisonwjy.charitree.views.setting.SettingFragment
import com.example.harrisonwjy.charitree.views.createdonation.IOnFocusListenable
import com.example.harrisonwjy.charitree.viewmodel.UserViewModel


fun Context.MainActivity(user: User): Intent {
    return Intent(this, MainActivity::class.java).apply {
        putExtra(INTENT_USER_ID, user.user_token)
    }
}

lateinit var toolbar: ActionBar
lateinit var mAdapter: BottomBarAdapter
private val INTENT_USER_ID = "user_token"

/**
 * MainActivity class that have four fragments (CamapaignsFragment, DonationsFragment, TreeFragment and SettingFragment attached to it
 * It also serve as a navigation between the stated fragments
 * @author Harrison Wong
 *
 */

class MainActivity : AppCompatActivity() {

    private val myViewModel: UserViewModel by viewModel()
    private lateinit var viewPager: LockableViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //setSupportActionBar(toolbar)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)

        viewPager = findViewById(R.id.viewPager)
        viewPager.offscreenPageLimit = 1

        setSupportActionBar(toolbar)

        val navigation = navigationView

        mAdapter = BottomBarAdapter(supportFragmentManager)
        mAdapter.addFragment(CampaignsFragment())
        mAdapter.addFragment(DonationsFragment())
        mAdapter.addFragment(TreeFragment())
        mAdapter.addFragment(SettingFragment())
        viewPager.adapter = mAdapter
        //adapter.(adapter)

        viewPager.setSwipeable(false)

        navigation.setOnNavigationItemSelectedListener(navigationListener)

    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        Log.e("MainActivity","Gained focus "+hasFocus +" "+ viewPager.currentItem)
     if (viewPager.currentItem == 3) {
            viewPager.adapter = mAdapter
            viewPager.setCurrentItem(3)
        }


        if(viewPager.currentItem == 1) {
            Log.e("MainActivity","Donation fragment viewed")
            //if (currentFragment is IOnFocusListenable) {
            val fragment = mAdapter.getItem(1)
                (fragment as IOnFocusListenable).onWindowFocusChanged(hasFocus)
            //}
        }

    }

    private val navigationListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
            var selectedFragment: Fragment? = null
            var fragmentTag: String? = null
            when (item.itemId) {
                R.id.campaign -> {
                    toolbar.title = "Campaigns"
                    //selectedFragment = CampaignsFragment.newInstance()
                    if(viewPager.currentItem == 0){
                        val adapter = mAdapter
                        val fragment = adapter.getItem(0) as CampaignsFragment
                        fragment.setViewPagerToFirst()
                    }else{
                        viewPager.setCurrentItem(0)
                    }
                }
                R.id.donation ->{
                    toolbar.title = "Donation"
                    viewPager.setCurrentItem(1)
                    val fragment = mAdapter.getItem(1)
                    (fragment as IOnFocusListenable).onWindowFocusChanged(true)
                }
                R.id.tree -> {
                    toolbar.title = "Tree"
                    viewPager.setCurrentItem(2)
                }
                R.id.setting -> {
                    toolbar.title = "Setting"
                    viewPager.setCurrentItem(3)
                    selectedFragment = SettingFragment.newInstance()
                }
            }
true
    }
}
