package com.example.harrisonwjy.charitree.views.setting

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.harrisonwjy.charitree.viewmodel.CampaignManagerViewModel

import com.example.harrisonwjy.charitree.R
import com.example.harrisonwjy.charitree.helper.HttpException
import com.example.harrisonwjy.charitree.helper.InputValidateShowError
import com.example.harrisonwjy.charitree.helper.SingleActionDialogBox
import com.example.harrisonwjy.charitree.helper.Validation
import com.example.harrisonwjy.charitree.model.request.RegisterCMRequest
import com.example.harrisonwjy.charitree.repo.CampaignRepo
import kotlinx.android.synthetic.main.fragment_cmregister.*
import org.koin.android.viewmodel.ext.android.viewModel

// TODO: Rename parameter arguments, choose names that match

/**
 * A RegisterCMFragment to hold the UI of the Register As Campaign Manager Screen
 * The RegisterCMFragment consist of a two text inputs (for UEN and Organisation button), a button to retrieve the organisation name, and a submit button
 */
class RegisterCMFragment : Fragment(),Validation,HttpException {

    private val campaignManagerViewModel: CampaignManagerViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_cmregister, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //animation_view.setMinAndMaxProgress(0f,1f)
        animation_view.scale = 1.3f

        // check user is campaign manager
        // if so, show button to switch, if not, show button to register

        input_org_name.addTextChangedListener(InputValidateShowError("normal",input_org_name,layout_org_name,getString(R.string.error_message_org_name)))
        input_uen.addTextChangedListener(InputValidateShowError("normal",input_uen,layout_uen,getString(R.string.error_message_uen)))

        // get token
        val prefs = getActivity()!!.getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE)
        val token: String = prefs.getString("token", "")//"No name defined" is the default value.
        val email: String = prefs.getString("email","")

        registerCMButton.setOnClickListener {
            indeterminateBar.visibility = View.VISIBLE
            // retrieve inputted text from the respective Edit Text (or Textfield)

            tagLineTextView.visibility = View.INVISIBLE
            layout_org_name.visibility = View.INVISIBLE
            input_org_name.visibility = View.INVISIBLE
            layout_uen.visibility = View.INVISIBLE
            input_uen.visibility = View.INVISIBLE
            retrieveNameButton.visibility = View.INVISIBLE
            registerCMButton.visibility = View.INVISIBLE


            isValidInput(input_org_name,layout_org_name,"Please enter a valid email")
            isValidInput(input_uen,layout_uen,"Please enter a password")

            // create a Request object
            val registerCM = RegisterCMRequest.create()
            // stores the email and password into the Request object
            registerCM.organization_name = input_org_name.text.toString()
            registerCM.UEN = input_uen.text.toString()

            // ViewModel will call Authenticate method
            campaignManagerViewModel.register(CampaignRepo(email,token),registerCM).observe(this,android.arch.lifecycle.Observer {

                // make a fake delay response
                try {
                    //set time in mili
                    Thread.sleep(1000)

                } catch (e: Exception) {
                    e.printStackTrace()
                }
                indeterminateBar.visibility = View.INVISIBLE
                // If user_token contains something
                if(it?.status == 1){
                    animation_view.visibility = View.VISIBLE
                    backToSettingButton.visibility = View.VISIBLE
                    animation_view.playAnimation()
                }else{
                    tagLineTextView.visibility = View.VISIBLE
                    layout_org_name.visibility = View.VISIBLE
                    input_org_name.visibility = View.VISIBLE
                    layout_uen.visibility = View.VISIBLE
                    input_uen.visibility = View.VISIBLE
                    registerCMButton.visibility = View.VISIBLE
                    retrieveNameButton.visibility = View.VISIBLE
                    val dialogBox = SingleActionDialogBox.newInstance("Opps! Something went wrong", it?.errors?.message)
                    dialogBox.show(fragmentManager,"fragment_alert")
                }
            })
            //loginButton.text = myViewModel.authenticate(email.text.toString(),password.text.toString())
//            myViewModel.getProjectDetail().observe(this, android.arch.lifecycle.Observer {
//                registerButton.text = it
//            })
        }

        backToSettingButton.setOnClickListener {
            activity?.finish()
        }

        retrieveNameButton.setOnClickListener {
            val uen = input_uen.text.toString()

            campaignManagerViewModel.getOrgNameByUEN(CampaignRepo(email,token),uen).observe(this,android.arch.lifecycle.Observer {
                // If user_token contains something
                if(it?.status == 1){
                    input_org_name.setText(it.organization_name!!)
                    Log.e("RegisterCMFragment","Organisation name is "+ it.organization_name!!)
                }else{
                    Log.e("RegisterCMFragment","Organisation name is FALIED!!")
                    val dialogBox = SingleActionDialogBox.newInstance("Opps! Something went wrong", it?.errors?.message)
                    dialogBox.show(fragmentManager,"fragment_alert")
                }
            })
        }
    }

    companion object {
        fun newInstance(): RegisterCMFragment {
            return RegisterCMFragment()
        }

    }
}
