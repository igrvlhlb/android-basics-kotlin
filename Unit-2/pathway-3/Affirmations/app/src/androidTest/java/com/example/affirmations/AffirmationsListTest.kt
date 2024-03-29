package com.example.affirmations

import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AffirmationsListTest {

    @get:Rule
    val activity = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun scroll_to_item() {
        onView(withId(R.id.recycler_view)).perform(
            RecyclerViewActions
                .scrollTo<RecyclerView.ViewHolder>(
                    hasDescendant(withText(R.string.affirmation10))
                )
        )

        onView(withText(R.string.affirmation10))
            .check(matches(isDisplayed()))
    }

    @Test
    fun scroll_to_neighbor() {
        onView(withId(R.id.recycler_view)).perform(
            RecyclerViewActions
                .scrollTo<RecyclerView.ViewHolder>(
                    hasDescendant(withText(R.string.affirmation5))
                )
        )
        onView(withText(R.string.affirmation4))
            .check(matches(isDisplayed()))
        onView(withText(R.string.affirmation6))
            .check(matches(isDisplayed()))
    }

    @Test
    fun scroll_should_not_exist() {
        onView(withId(R.id.recycler_view)).perform(
            RecyclerViewActions
                .scrollTo<RecyclerView.ViewHolder>(
                    hasDescendant(withText(R.string.affirmation10))
                )
        )
        onView(withText(R.string.affirmation1))
            .check(doesNotExist())
    }
}