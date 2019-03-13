package com.example.harrisonwjy.charitree.views.onboarding

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.harrisonwjy.charitree.R
import com.example.harrisonwjy.charitree.viewmodel.UserViewModel
import com.example.harrisonwjy.charitree.helper.HttpException
import com.example.harrisonwjy.charitree.helper.InputValidateShowError
import com.example.harrisonwjy.charitree.helper.SingleActionDialogBox
import com.example.harrisonwjy.charitree.helper.Validation
import com.example.harrisonwjy.charitree.model.request.UserRegisterRequest
import com.example.harrisonwjy.charitree.repo.TradAuthenticationRepo
import kotlinx.android.synthetic.main.fragment_register.*
import org.koin.android.viewmodel.ext.android.viewModel

// TODO: Rename parameter arguments, choose names that match

/**
 * A RegisterFragment holds the UI of the Register Screen as a User
 * The RegisterFragment consist of five text inputs (email, first, last name, password and confirm password) and a button to register
 */
class RegisterFragment : Fragment(), Validation, HttpException {

    val myViewModel: UserViewModel by viewModel()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        input_email.addTextChangedListener(InputValidateShowError("email",input_email,layout_email,getString(R.string.error_message_email)))
        input_firstname.addTextChangedListener(InputValidateShowError("normal",input_firstname,layout_firstname,getString(R.string.error_message_generic)))
        input_lastname.addTextChangedListener(InputValidateShowError("normal",input_lastname,layout_lastname,getString(R.string.error_message_generic)))
        input_password.addTextChangedListener(InputValidateShowError("password",input_password,layout_password,getString(R.string.error_message_password)))
        input_passwordconfirm.addTextChangedListener(InputValidateShowError("password",input_passwordconfirm,layout_passwordconfirm,getString(R.string.error_message_password)))

        registerButton.setOnClickListener {

            isValidEmail(input_email,layout_email,"Please enter a valid email")
            isValidInput(input_firstname,layout_firstname,"Please enter a first name")
            isValidInput(input_lastname,layout_lastname,"Please enter a last name")
            isValidInput(input_password,layout_password,"Please enter a password of at least 8 characters")
            isValidInput(input_passwordconfirm,layout_passwordconfirm,"Please enter a password of at least 8 characters")


            if(isPasswordsMatch(input_password,input_passwordconfirm,layout_passwordconfirm,"Password does not match") == true){
                Log.e("RegisterFragment","Password matched")
            }else{
                Log.e("RegisterFragment","Password doesnt match")
                val dialogBox = SingleActionDialogBox.newInstance("Opps! Something went wrong","Password doesn't match")
                //val dialogBox = SingleActionDialogBox.newInstance("Opps! Something went wrong",onHttpException(it!!.httpStatus))
                dialogBox.show(fragmentManager,"fragment_alert")
                return@setOnClickListener
            }

            indeterminateBar.visibility = View.VISIBLE
            tagLineTextView.visibility = View.INVISIBLE
            tagLineTextView2.visibility = View.INVISIBLE
            layout_email.visibility = View.INVISIBLE
            input_email.visibility = View.INVISIBLE
            layout_firstname.visibility = View.INVISIBLE
            input_firstname.visibility = View.INVISIBLE
            layout_lastname.visibility = View.INVISIBLE
            input_lastname.visibility = View.INVISIBLE
            layout_password.visibility = View.INVISIBLE
            input_password.visibility = View.INVISIBLE
            layout_passwordconfirm.visibility = View.INVISIBLE
            input_passwordconfirm.visibility = View.INVISIBLE
            registerButton.visibility = View.INVISIBLE
            goBackToLoginButton.visibility = View.INVISIBLE

            val repo = TradAuthenticationRepo()
            val request = UserRegisterRequest()
            request.email = input_email.text.toString()
            request.first_name = input_firstname.text.toString()
            request.last_name = input_lastname.text.toString()
            request.password = input_passwordconfirm.text.toString()

            myViewModel.register(TradAuthenticationRepo(),request).observe(this,android.arch.lifecycle.Observer {
                //Log.e("LoginFragment","LoginFragment received "+ it?.user_token + " " + it?.status)

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
                    val fragmentManager = fragmentManager
                    val fragmentTransaction = fragmentManager!!.beginTransaction()
                    fragmentTransaction.replace(R.id.fragment_frame, SuccessRegisterFragment.newInstance())
                            .addToBackStack("RegisterFragment")
                    fragmentTransaction.commit()


                }else{
                    indeterminateBar.visibility = View.INVISIBLE
                    tagLineTextView.visibility = View.VISIBLE
                    tagLineTextView2.visibility = View.VISIBLE
                    layout_email.visibility = View.VISIBLE
                    input_email.visibility = View.VISIBLE
                    layout_firstname.visibility = View.VISIBLE
                    input_firstname.visibility = View.VISIBLE
                    layout_lastname.visibility = View.VISIBLE
                    input_lastname.visibility = View.VISIBLE
                    layout_password.visibility = View.VISIBLE
                    input_password.visibility = View.VISIBLE
                    layout_passwordconfirm.visibility = View.VISIBLE
                    input_passwordconfirm.visibility = View.VISIBLE
                    registerButton.visibility = View.VISIBLE
                    goBackToLoginButton.visibility = View.VISIBLE
                    val dialogBox = SingleActionDialogBox.newInstance("Opps! Something went wrong",it?.errors?.message)
                    //val dialogBox = SingleActionDialogBox.newInstance("Opps! Something went wrong",onHttpException(it!!.httpStatus))
                    dialogBox.show(fragmentManager,"fragment_alert")
                }

            })

        }

        // go back to login button
        goBackToLoginButton.setOnClickListener {
            val fm = fragmentManager
            if (fm!!.backStackEntryCount > 0) {
                Log.i("RegisterFragment", "popping backstack")
                fm.popBackStack()
            } else {
                Log.i("RegisterFragment", "nothing on backstack, calling super")
                val fragmentTransaction = fragmentManager!!.beginTransaction()
                fragmentTransaction.replace(R.id.fragment_frame, LoginFragment.newInstance())
                        .addToBackStack("asdasd")
                fragmentTransaction.commit()
            }
        }
    }

    companion object {
        fun newInstance(): RegisterFragment {
            return RegisterFragment()
        }

    }
}
