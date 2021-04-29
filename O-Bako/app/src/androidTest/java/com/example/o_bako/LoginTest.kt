package com.example.o_bako

import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginTest{
    @Rule
    @JvmField
    var activityTestRule = ActivityTestRule(Login::class.java)

    @Test
    fun TestLogin(){
        onView(withId(R.id.input_login)).perform(typeText(""))
        onView(withId(R.id.input_password)).perform(typeText(""))
        onView(withId(R.id.btn_login)).perform(click())
    }

    @Test
    fun TestSignUp(){
        onView(withId(R.id.btn_signup)).perform(click())
        onView(withId(R.id.reg_username)).perform(typeText("Impostors"))
        onView(withId(R.id.reg_passwd)).perform(typeText("ImpostorsTeam"))
        onView(withId(R.id.reg_email)).perform(typeText("ImpostorsTeam@gmail.com"))
        closeSoftKeyboard()
        onView(withId(R.id.btn_register)).perform(click())
        onView(withId(R.id.btn_login)).perform(click())
    }
}