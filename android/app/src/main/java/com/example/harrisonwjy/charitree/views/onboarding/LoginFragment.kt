package com.example.harrisonwjy.charitree.views.onboarding

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.harrisonwjy.charitree.R
import com.example.harrisonwjy.charitree.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.android.viewmodel.ext.android.viewModel
import android.content.Context.MODE_PRIVATE
import com.example.harrisonwjy.charitree.views.MainActivity
import android.content.Intent
import com.example.harrisonwjy.charitree.views.campaignmanager.CampaignManagerActivity
import com.example.harrisonwjy.charitree.helper.*
import com.example.harrisonwjy.charitree.model.request.LoginRequest
import com.example.harrisonwjy.charitree.repo.TradAuthenticationRepo

/**
 * A LoginFragment holds the UI of the Login Screen
 * The LoginFragment consist of two text inputs and a button to login
 */
class LoginFragment : Fragment(),Validation, HttpException {
    // TODO: Rename and change types of parameters
    private val myViewModel: UserViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        input_email.addTextChangedListener(InputValidateShowError("email",input_email,layout_email,getString(R.string.error_message_email)))
        input_password.addTextChangedListener(InputValidateShowError("normal",input_password,layout_password,getString(R.string.error_message_password)))


        loginButton.setOnClickListener{
            indeterminateBar.visibility = View.VISIBLE
            // retrieve inputted text from the respective Edit Text (or Textfield)
            val getEmailAddress = input_email.text.toString()
            val getPassword = input_password.text.toString()

            logo.visibility = View.INVISIBLE
            tagLineTextView.visibility = View.INVISIBLE
            layout_email.visibility = View.INVISIBLE
            input_email.visibility = View.INVISIBLE
            layout_password.visibility = View.INVISIBLE
            input_password.visibility = View.INVISIBLE
            loginButton.visibility = View.INVISIBLE
            registerButton.visibility = View.INVISIBLE


            isValidEmail(input_email,layout_email,"Please enter a valid email")
            isValidInput(input_password,layout_password,"Please enter a password")

            // create a Request object
            val loginRequest = LoginRequest.create()
            // stores the email and password into the Request object
            loginRequest.email = getEmailAddress
            loginRequest.password = getPassword

            // ViewModel will call Authenticate method
            myViewModel.authenticate(TradAuthenticationRepo(),loginRequest).observe(this,android.arch.lifecycle.Observer {


                // make a fake delay response
                try {
                    //set time in mili
                    Thread.sleep(1500)

                } catch (e: Exception) {
                    e.printStackTrace()
                }

                // If user_token contains something
                if(it?.status == 1){
                    indeterminateBar.visibility = View.INVISIBLE
                    // Store user_token into SharedPreferences
                    // What is Shared Preferenes? https://developer.android.com/training/data-storage/shared-preferences
                    // See: https://stackoverflow.com/questions/40043166/shared-prefrences-to-save-a-authentication-token-in-android

                    val prefs = activity?.getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                    val editor = prefs?.edit()
                    editor?.putString("token", it?.user_token)
                    editor?.putString("email",getEmailAddress)
                    editor?.apply()
                    val mode = prefs?.getString("mode","user")

                    if(mode == "user"){
                        val intent = Intent(activity, MainActivity::class.java)
                        activity?.startActivity(intent)
                        activity?.finish()
                    }else if (mode=="campaignmanager"){
                        val intent = Intent(activity, CampaignManagerActivity::class.java)
                        activity?.startActivity(intent)
                        activity?.finish()
                    }else{
                        val intent = Intent(activity, MainActivity::class.java)
                        activity?.startActivity(intent)
                        activity?.finish()
                    }

                }else{
                    logo.visibility = View.VISIBLE
                    tagLineTextView.visibility = View.VISIBLE
                    layout_email.visibility = View.VISIBLE
                    input_email.visibility = View.VISIBLE
                    layout_password.visibility = View.VISIBLE
                    input_password.visibility = View.VISIBLE
                    loginButton.visibility = View.VISIBLE
                    registerButton.visibility = View.VISIBLE
                    indeterminateBar.visibility = View.INVISIBLE
                    val test: ArrayList<String> = ArrayList()
                    test.add("asdasdas")
                    val dialogBox = SingleActionDialogBox.newInstance("Opps! Something went wrong",it?.errors?.message)
                    //val dialogBox = SingleActionDialogBox.newInstance("Opps! Something went wrong",onHttpException(it!!.httpStatus))
                    dialogBox.show(fragmentManager,"fragment_alert")
                }

            })
        }

        // When user taps on Register button, goes to Register Screen
        registerButton.setOnClickListener {
            val fragmentManager = fragmentManager
            val fragmentTransaction = fragmentManager!!.beginTransaction()
            fragmentTransaction.replace(R.id.fragment_frame, RegisterFragment.newInstance())
                    .addToBackStack("RegisterFragment")
            fragmentTransaction.commit()
        }
    }




    // ugly code here. will remove and put it somewhere or in the trash
    override fun unauthorized(): String {
        return "Unauthorized access!!!"
    }

    override fun unprocessableEntity(): String {
        return "Field missing"
    }

    override fun badRequest(): String {
        return "bad request"
    }

    companion object {
        fun newInstance(): LoginFragment {
            return LoginFragment()
        }
    }

}

