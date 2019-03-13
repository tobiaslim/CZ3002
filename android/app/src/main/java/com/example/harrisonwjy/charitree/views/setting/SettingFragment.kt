package com.example.harrisonwjy.charitree.views.setting

import android.arch.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.harrisonwjy.charitree.viewmodel.CampaignManagerViewModel
import com.example.harrisonwjy.charitree.R

import com.example.harrisonwjy.charitree.views.onboarding.OnboardingActivity
import com.example.harrisonwjy.charitree.repo.CampaignRepo
import kotlinx.android.synthetic.main.fragment_setting.*
import org.koin.android.viewmodel.ext.android.viewModel
import com.example.harrisonwjy.charitree.views.campaignmanager.CampaignManagerActivity
import com.example.harrisonwjy.charitree.model.request.RegisterCMRequest
import com.example.harrisonwjy.charitree.views.MainActivity


// TODO: Rename parameter arguments, choose names that match

/**
 * A SettingFragment to hold the UI of the Setting Screen
 * The SettingFragment has a button to switch/register as a Campaign Manager and Log off
 */
class SettingFragment : Fragment() {

    private val campaignManagerViewModel: CampaignManagerViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        logOffButton.setOnClickListener {

            val pref = getActivity()!!.getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE)
            val editor = pref.edit()
            editor.remove("token")
            editor.remove("email")
            editor.remove("mode")

            // Save the changes in SharedPreferences
            editor.apply()

            val intent = Intent(activity, OnboardingActivity::class.java)
            activity?.startActivity(intent)
            activity?.finish()


        }

        val prefs = getActivity()!!.getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE)
        val editor = prefs.edit()
        val token: String = prefs.getString("token", "")//"No name defined" is the default value.
        val email: String = prefs.getString("email","")
        val mode: String = prefs.getString("mode","user")

        // create a Request object
        val request = RegisterCMRequest.create() // empty request for now
        // stores the email and password into the Request object

        //campaignManagerViewModel.verifyCM(CampaignRepo(email,token),request)
        campaignManagerViewModel.getCampaignManagerAccess(CampaignRepo(email,token),request)!!.observe(this, Observer {
            val isCampaignManager = it?.status

            when(mode){
                "user"->{
                    if(isCampaignManager == 1){
                        Log.e("User - SettingFragment","Show button to switch")
                        registerAsCampaignManagerButton.setText("Switch to Campaign Manager mode")

                    }else{
                        Log.e("User - SettingFragment","Show button to register")
                        registerAsCampaignManagerButton.setText("Register as Campaign Manager")
                    }
                    registerAsCampaignManagerButton.visibility = View.VISIBLE
                }
                "campaignmanager"->{
                    Log.e("CM - SettingFragment","Show button to switch")
                    registerAsCampaignManagerButton.text = "Switch to User mode"
                    registerAsCampaignManagerButton.visibility = View.VISIBLE
                }
                else->{
                    registerAsCampaignManagerButton.visibility = View.INVISIBLE
                }
            }


            registerAsCampaignManagerButton.setOnClickListener{
                when(mode){
                    "user"->{
                        if(isCampaignManager == 1){
                            val intent = Intent(activity, CampaignManagerActivity::class.java)
                            val editor = activity?.getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE)?.edit()
                            activity?.startActivity(intent)
                            editor?.putString("mode","campaignmanager")
                            editor?.apply()
                            activity?.finish()
                        }else{
                            val intent = Intent(activity, RegisterCMActivity::class.java)
                            activity?.startActivity(intent)
                        }
                    }
                    "campaignmanager"->{
                        val intent = Intent(activity, MainActivity::class.java)
                        activity?.startActivity(intent)
                        editor?.putString("mode","user")
                        editor?.apply()
                        activity?.finish()
                    }
                    else->{

                    }
                }
            }

        })
    }
    companion object {
        fun newInstance(): SettingFragment {
            return SettingFragment()
        }

    }
}
