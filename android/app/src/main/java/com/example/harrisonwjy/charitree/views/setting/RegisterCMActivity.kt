package com.example.harrisonwjy.charitree.views.setting

import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import com.example.harrisonwjy.charitree.R
import android.util.Log

/**
 * RegisterCMActivity holds the RegisterCMFragment
 */
class RegisterCMActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registercm)

        // onboarding
        supportFragmentManager.beginTransaction()
                .add(R.id.fragment_frame, RegisterCMFragment.newInstance())
                .commit()

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        Log.v("RegisterCMActivity","Stack count is "+ supportFragmentManager.backStackEntryCount)
        return true
    }

}
