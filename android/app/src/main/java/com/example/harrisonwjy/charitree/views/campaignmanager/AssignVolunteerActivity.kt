package com.example.harrisonwjy.charitree.views.campaignmanager

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.example.harrisonwjy.charitree.R
import com.example.harrisonwjy.charitree.helper.SingleActionDialogBox
import com.example.harrisonwjy.charitree.model.request.ChangeStatusDonationRequest
import com.example.harrisonwjy.charitree.repo.CampaignRepo
import com.example.harrisonwjy.charitree.viewmodel.CampaignViewModel
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * AssignVolunteerActivity class holds the UI for assigning of volunteer to the donation
 * It consist of two text input (name and handphone number) and a button to submit
 * @author Wang Lu
 */
class AssignVolunteerActivity : AppCompatActivity() {

    private val campaignViewModel : CampaignViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_assign_volunteer)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)

        setSupportActionBar(toolbar)

        val registerButton = findViewById<Button>(R.id.registerButton)

        registerButton.setOnClickListener {

            val getVolunteerName = findViewById<EditText>(R.id.input_volunteer_name)
            val getVolunteerHp = findViewById<EditText>(R.id.input_volunteer_hp)

            val request = ChangeStatusDonationRequest().apply{
                action = "in-progress"
                volunteer_name = getVolunteerName.text.toString()
                volunteer_HP = getVolunteerHp.text.toString()
            }

            val prefs = getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE)
            val token: String = prefs.getString("token", "")//"No name defined" is the default value.
            val email: String = prefs.getString("email","")
            val donationId = intent.getIntExtra("donationId",0)

            campaignViewModel.changeStatusByDID(CampaignRepo(email,token),donationId,request).observe(this,android.arch.lifecycle.Observer {
                //Log.e("LoginFragment","LoginFragment received "+ it?.user_token + " " + it?.status)

                // If user_token contains something
                if(it?.status == 1){
                    Log.e("CAA","Assign volunteer successfull")
                    this.finish()
                }else {
                    val dialogBox = SingleActionDialogBox.newInstance("Opps! Something went wrong",it?.errors?.message)
                    dialogBox.showsDialog = true
                }
            })
        }

    }

}
