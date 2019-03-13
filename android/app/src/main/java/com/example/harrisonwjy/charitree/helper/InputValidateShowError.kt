package com.example.harrisonwjy.charitree.helper

import android.support.design.widget.TextInputLayout
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import com.example.harrisonwjy.charitree.R


class InputValidateShowError constructor(private val inputType: String, private val input_field: EditText, private val input_layout :TextInputLayout, private val message : CharSequence) : TextWatcher,Validation {

    override fun afterTextChanged(p0: Editable?) {
        return
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        return
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        when(inputType){
            "email" -> {
                isValidEmail(input_field,input_layout,message)
            }
            "normal"->{
                isValidInput(input_field,input_layout,message)
            }
            "password"->{
                isValidPassword(input_field,input_layout,message)
            }
        }

    }
}




