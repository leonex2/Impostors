package com.example.o_bako

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions
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
    fun TestSignUp(){
        onView(withId(R.id.btn_signup)).perform(ViewActions.click())
        onView(withId(R.id.reg_username)).perform(ViewActions.typeText("Impostors"))
        onView(withId(R.id.reg_passwd)).perform(ViewActions.typeText("ImpostorsTeam"))
        onView(withId(R.id.btn_register)).perform(ViewActions.click())
    }
    @Test
    fun TestLogin(){
        onView(withId(R.id.input_login)).perform(ViewActions.typeText("Impostors"))
        onView(withId(R.id.input_password)).perform(ViewActions.typeText("ImpostorsTeam"))
        onView(withId(R.id.btn_login)).perform(ViewActions.click())
        pressBack()
    }
}