@file:Suppress("DEPRECATION")

package com.wipro.factapp

import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.test.InstrumentationRegistry
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.actionWithAssertions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItem
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.wipro.factapp.feautres.factmodule.FactActivity

import io.reactivex.Single
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import com.readystatesoftware.chuck.internal.ui.MainActivity
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.Espresso.onView
import org.hamcrest.Matchers.*
import androidx.test.espresso.IdlingRegistry
import org.junit.After


@RunWith(AndroidJUnit4::class)
public class FactActivityTest {


    @Rule
    @JvmField
    public val mActivityRule: ActivityTestRule<FactActivity> = ActivityTestRule(
        FactActivity::class.java
    )


    @Test
    fun testFactRecycleView() {

        /* val rv = onView(allOf(withId(R.id.rv_fact), isDisplayed()))
         rv.perform(actionWithAssertions(click()))*/
        onView(withId(R.id.rv_fact)).perform(click()).check(matches(isDisplayed()))

        onView(withId(R.id.rv_fact))
            .perform(click());

        val activity = mActivityRule.getActivity()
        onView(withText("Title : 'IT' Rating : '7.6'")).inRoot(withDecorView(not(`is`(activity.getWindow().getDecorView()))))
            .check(matches(isDisplayed()))

    }

    @Test
    fun testOnNetworkErrorState() {

        onView(withId(R.id.refresh)).perform(click())
    }


}