package com.example.harrisonwjy.charitree.views.onboarding

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import com.example.harrisonwjy.charitree.R
import android.util.Log


fun Context.OnboardingActivity(): Intent {
    return Intent(this, OnboardingActivity::class.java).apply {
    }
}

/**
 * OnboardingActivity class is class that holds the GetStartedFragment
 * This class is intialize ONLY when the app have a invalid or no token stored in the app (SharedPreference)
 */
class OnboardingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        // onboarding
        supportFragmentManager.beginTransaction()
                .add(R.id.fragment_frame, GetStartedFragment.newInstance())
                .commit()

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        Log.v("Onboarding","Stack count is "+ supportFragmentManager.backStackEntryCount)
        return true
    }
}
