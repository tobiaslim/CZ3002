package com.example.harrisonwjy.charitree.views.onboarding

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.harrisonwjy.charitree.R
import com.example.harrisonwjy.charitree.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_success_register.*
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * A SuccessRegisterFragment holds the UI of a Successful Registeration of a user.
 * After a User has successfully registered as a user, this fragment will be shown to the user
 */
class SuccessRegisterFragment : Fragment() {
    // TODO: Rename and change types of parameters

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_success_register, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        goBackToLoginButton.setOnClickListener {
            fragmentManager?.popBackStack("RegisterFragment", FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
    }

    companion object {
        fun newInstance(): SuccessRegisterFragment {
            return SuccessRegisterFragment()
        }

    }
}
