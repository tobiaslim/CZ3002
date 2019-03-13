package com.example.harrisonwjy.charitree

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.espresso.matcher.RootMatchers.isDialog
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.example.harrisonwjy.charitree.views.onboarding.OnboardingActivity
import kotlinx.android.synthetic.main.fragment_login.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Before
import com.example.harrisonwjy.charitree.views.MainActivity
import android.support.test.InstrumentationRegistry.getTargetContext
import android.content.ComponentName
import org.hamcrest.CoreMatchers.endsWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class SomeTest {

    @get:Rule
    val activity = IntentsTestRule<OnboardingActivity>(OnboardingActivity::class.java)

    @Before
    fun init() {
        activity.getActivity()
                .getSupportFragmentManager().beginTransaction()
    }

//    @Test
//    fun test1_register_with_invalid_email(){
//        onView(withId(R.id.logo)).check(matches((isDisplayed())))
//        onView(withId(R.id.getStartedButton)).perform(click())
//        onView(withId(R.id.registerButton)).perform(click())
//        onView(withId(R.id.input_firstname)).perform(typeText("test first name"))
//        onView(withId(R.id.input_firstname)).perform(pressImeActionButton())
//        onView(withId(R.id.input_lastname)).perform(typeText("test last name"))
//        onView(withId(R.id.input_lastname)).perform(pressImeActionButton())
//        onView(withId(R.id.input_password)).perform(typeText("password"))
//        onView(withId(R.id.input_password)).perform(pressImeActionButton())
//        onView(withId(R.id.input_passwordconfirm)).perform(typeText("password"))
//        onView(withId(R.id.input_passwordconfirm)).perform(pressImeActionButton())
//        onView(withId(R.id.registerButton)).perform(click())
//        onView(withText("Opps! Something went wrong"))
//                .inRoot(isDialog()) // <---
//                .check(matches(isDisplayed()))
//    }
//
//    @Test
//    fun test2_register_with_invalid_first_name(){
//        onView(withId(R.id.logo)).check(matches((isDisplayed())))
//        onView(withId(R.id.getStartedButton)).perform(click())
//        onView(withId(R.id.registerButton)).perform(click())
//        onView(withId(R.id.input_email)).perform(typeText("test@hotmail.com"))
//        onView(withId(R.id.input_email)).perform(pressImeActionButton())
//        onView(withId(R.id.input_lastname)).perform(typeText("test last name"))
//        onView(withId(R.id.input_lastname)).perform(pressImeActionButton())
//        onView(withId(R.id.input_password)).perform(typeText("password"))
//        onView(withId(R.id.input_password)).perform(pressImeActionButton())
//        onView(withId(R.id.input_passwordconfirm)).perform(typeText("password"))
//        onView(withId(R.id.input_passwordconfirm)).perform(pressImeActionButton())
//        onView(withId(R.id.registerButton)).perform(click())
//        onView(withText("Opps! Something went wrong"))
//                .inRoot(isDialog()) // <---
//                .check(matches(isDisplayed()))
//    }
//
//    @Test
//    fun test3_register_with_invalid_last_name(){
//        onView(withId(R.id.logo)).check(matches((isDisplayed())))
//        onView(withId(R.id.getStartedButton)).perform(click())
//        onView(withId(R.id.registerButton)).perform(click())
//        onView(withId(R.id.input_email)).perform(typeText("test@hotmail.com"))
//        onView(withId(R.id.input_email)).perform(pressImeActionButton())
//        onView(withId(R.id.input_firstname)).perform(typeText("test first name"))
//        onView(withId(R.id.input_firstname)).perform(pressImeActionButton())
//        onView(withId(R.id.input_password)).perform(typeText("password"))
//        onView(withId(R.id.input_password)).perform(pressImeActionButton())
//        onView(withId(R.id.input_passwordconfirm)).perform(typeText("password"))
//        onView(withId(R.id.input_passwordconfirm)).perform(pressImeActionButton())
//        onView(withId(R.id.registerButton)).perform(click())
//        onView(withText("Opps! Something went wrong"))
//                .inRoot(isDialog()) // <---
//                .check(matches(isDisplayed()))
//    }
//
//    @Test
//    fun test4_register_with_invalid_empty_passwords(){
//        onView(withId(R.id.logo)).check(matches((isDisplayed())))
//        onView(withId(R.id.getStartedButton)).perform(click())
//        onView(withId(R.id.registerButton)).perform(click())
//        onView(withId(R.id.input_email)).perform(typeText("test@hotmail.com"))
//        onView(withId(R.id.input_email)).perform(closeSoftKeyboard())
//        onView(withId(R.id.input_firstname)).perform(typeText("test first name"))
//        onView(withId(R.id.input_firstname)).perform(closeSoftKeyboard())
//        onView(withId(R.id.input_lastname)).perform(typeText("test last name"))
//        onView(withId(R.id.input_lastname)).perform(closeSoftKeyboard())
//        onView(withId(R.id.registerButton)).perform(click())
//        onView(withText("Opps! Something went wrong"))
//                .inRoot(isDialog()) // <---
//                .check(matches(isDisplayed()))
//    }
//
//    @Test
//    fun test5_register_with_invalid_passwords(){
//        onView(withId(R.id.logo)).check(matches((isDisplayed())))
//        onView(withId(R.id.getStartedButton)).perform(click())
//        onView(withId(R.id.registerButton)).perform(click())
//        onView(withId(R.id.input_email)).perform(typeText("test@hotmail.com"))
//        onView(withId(R.id.input_email)).perform(pressImeActionButton())
//        onView(withId(R.id.input_firstname)).perform(typeText("test first name"))
//        onView(withId(R.id.input_firstname)).perform(pressImeActionButton())
//        onView(withId(R.id.input_lastname)).perform(typeText("test last name"))
//        onView(withId(R.id.input_lastname)).perform(pressImeActionButton())
//        onView(withId(R.id.input_password)).perform(typeText("password1"))
//        onView(withId(R.id.input_password)).perform(pressImeActionButton())
//        onView(withId(R.id.input_passwordconfirm)).perform(typeText("password2"))
//        onView(withId(R.id.input_passwordconfirm)).perform(pressImeActionButton())
//        onView(withId(R.id.registerButton)).perform(click())
//        onView(withText("Opps! Something went wrong"))
//                .inRoot(isDialog()) // <---
//                .check(matches(isDisplayed()))
//    }
//
//    @Test
//    fun test6_register_with_invalid_passwords_characters(){
//        onView(withId(R.id.logo)).check(matches((isDisplayed())))
//        onView(withId(R.id.getStartedButton)).perform(click())
//        onView(withId(R.id.registerButton)).perform(click())
//        onView(withId(R.id.input_email)).perform(typeText("test@hotmail.com"))
//        onView(withId(R.id.input_email)).perform(pressImeActionButton())
//        onView(withId(R.id.input_firstname)).perform(typeText("test first name"))
//        onView(withId(R.id.input_firstname)).perform(pressImeActionButton())
//        onView(withId(R.id.input_lastname)).perform(typeText("test last name"))
//        onView(withId(R.id.input_lastname)).perform(pressImeActionButton())
//        onView(withId(R.id.input_password)).perform(typeText("password1"))
//        onView(withId(R.id.input_password)).perform(pressImeActionButton())
//        onView(withId(R.id.input_passwordconfirm)).perform(typeText("password1"))
//        onView(withId(R.id.input_passwordconfirm)).perform(pressImeActionButton())
//        onView(withId(R.id.registerButton)).perform(click())
//        onView(withText("Opps! Something went wrong"))
//                .inRoot(isDialog()) // <---
//                .check(matches(isDisplayed()))
//    }
//
//    @Test
//    fun test7_register_with_all_valid(){
//        onView(withId(R.id.logo)).check(matches((isDisplayed())))
//        onView(withId(R.id.getStartedButton)).perform(click())
//        onView(withId(R.id.registerButton)).perform(click())
//        onView(withId(R.id.input_email)).perform(typeText("test@hotmail.com"))
//        onView(withId(R.id.input_email)).perform(pressImeActionButton())
//        onView(withId(R.id.input_firstname)).perform(typeText("test first name"))
//        onView(withId(R.id.input_firstname)).perform(pressImeActionButton())
//        onView(withId(R.id.input_lastname)).perform(typeText("test second name"))
//        onView(withId(R.id.input_lastname)).perform(pressImeActionButton())
//        onView(withId(R.id.input_password)).perform(typeText("Password@1"))
//        onView(withId(R.id.input_password)).perform(pressImeActionButton())
//        onView(withId(R.id.input_passwordconfirm)).perform(typeText("Password@1"))
//        onView(withId(R.id.input_passwordconfirm)).perform(pressImeActionButton())
//        onView(withId(R.id.registerButton)).perform(click())
//        Thread.sleep(3000L)
//        onView(withId(R.id.goBackToLoginButton)).check(matches(isDisplayed()))
//        //onView(withText(endsWith("Back to Login"))).check(matches(isDisplayed()))
//    }


    @Test
    fun testA_Missing_Password(){
        onView(withId(R.id.logo)).check(matches((isDisplayed())))
        onView(withId(R.id.getStartedButton)).perform(click())
        onView(withId(R.id.input_email)).perform(typeText("Harrisonwjy@hotmail.com"))
        onView(withId(R.id.input_email)).perform(closeSoftKeyboard())
        //onView(withId(R.id.input_password)).perform(typeText(""))
        //onView(withId(R.id.input_password)).perform(pressImeActionButton())
        onView(withId(R.id.loginButton)).perform(click())
        onView(withText("Opps! Something went wrong"))
                .inRoot(isDialog()) // <---
                .check(matches(isDisplayed()))
    }

    @Test
    fun testB_Missing_Email(){
        onView(withId(R.id.logo)).check(matches((isDisplayed())))
        onView(withId(R.id.getStartedButton)).perform(click())
        onView(withId(R.id.input_email)).perform(typeText(""))
        onView(withId(R.id.input_email)).perform(pressImeActionButton())
        onView(withId(R.id.input_password)).perform(typeText("Password@1"))
        onView(withId(R.id.input_password)).perform(pressImeActionButton())
        onView(withId(R.id.loginButton)).perform(click())
        onView(withText("Opps! Something went wrong"))
                .inRoot(isDialog()) // <---
                .check(matches(isDisplayed()))
    }



    @Test
    fun testC_correctEmailAndPasswordTest(){
        onView(withId(R.id.logo)).check(matches((isDisplayed())))
        onView(withId(R.id.getStartedButton)).perform(click())
        onView(withId(R.id.input_email)).perform(typeText("harrison@gmail.com"))
        onView(withId(R.id.input_email)).perform(pressImeActionButton())
        onView(withId(R.id.input_password)).perform(typeText("P@ssword1"))
        onView(withId(R.id.input_password)).perform(pressImeActionButton())
        onView(withId(R.id.loginButton)).perform(click())
        //onView(withId(R.id.indeterminateBar)).check(matches((isDisplayed())))
        //onView(withId(R.id.viewPager)).check(matches((isDisplayed())))
        //Intents.init()
        //intended(hasComponent(MainActivity::class.java!!.getName()))
        Thread.sleep(3000L)
        intended(hasComponent(ComponentName(getTargetContext(), MainActivity::class.java)))
    }


}