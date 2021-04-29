package com.example.o_bako

import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SplashTest {
    @JvmField
    @Rule
    var splashTestRule = ActivityTestRule(SplashScreen::class.java)

    @Before
    fun setup(){
        Intents.init()
    }

    @Test
    fun splashTest(){
        Thread.sleep(1800)
        intended(hasComponent(Login::class.java.name))
    }
    @After
    fun tearDown(){
        Intents.release()
    }
}