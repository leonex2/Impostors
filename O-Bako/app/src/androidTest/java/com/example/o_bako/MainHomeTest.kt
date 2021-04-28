package com.example.o_bako

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.o_bako.fragments.MainHome
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainHomeTest {
    @Rule
    @JvmField
    var activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun TestNotifi() {
        onView(withId(R.id.notify_icon)).perform(ViewActions.click())
    }

    @Test
    fun TestPilihan() {
        onView(withId(R.id.veggies_opt)).perform(ViewActions.click())
    }

    @Test
    fun TestCheckNavigation(){
        onView(withId(R.id.icon_cart)).perform(ViewActions.click())
        onView(withId(R.id.icon_history)).perform(ViewActions.click())
        onView(withId(R.id.icon_setting)).perform(ViewActions.click())
        onView(withId(R.id.icon_home)).perform(ViewActions.click())
    }

    @Test
    fun TestEditAlamat(){
        onView(withId(R.id.icon_setting)).perform(ViewActions.click())
        onView(withId(R.id.edit_new_alamat)).perform(ViewActions.typeText("Jl. Thamrin No. 89"))
        closeSoftKeyboard()
        onView(withId(R.id.btn_edit_submit)).perform(ViewActions.click())
    }
    @Test
    fun TestCheckFriend(){
        onView(withId(R.id.invite_icon)).perform(ViewActions.click())
        pressBack()
    }
}