package com.example.ecommerceapp.ui

import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.ListFragment
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.isDialog
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.adevinta.android.barista.assertion.BaristaRecyclerViewAssertions.assertRecyclerViewItemCount
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.adevinta.android.barista.internal.performAction
import com.example.ecommerceapp.MainActivity
import com.example.ecommerceapp.R
import com.example.ecommerceapp.launchFragmentInHiltContainer
import com.example.ecommerceapp.ui.product_details.ProductDetailsFragment
import com.example.ecommerceapp.ui.product_list.ProductFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Rule

@RunWith(AndroidJUnit4::class)
class ProductListFeature {

    val mActivityRule = ActivityTestRule(MainActivity::class.java)
        @Rule get

    @Test
    fun displayScreenTitle() {
        assertDisplayed("Products")
    }

    @Test
    fun displaySearchEdittext() {
        assertDisplayed(R.id.searchView)
    }

    @Test
    fun clearSearchTextOnButtonClick(){
        onView(withId(R.id.searchView)).perform(replaceText("SampleTextToReplace"))
        onView(withId(R.id.clearSearchText)).perform(click())
        onView(withId(R.id.searchView)).check(matches(withText("")))
    }

    @Test
    fun displayFilterButton() {
        assertDisplayed(R.id.filterButton)
    }

    @Test
    fun displayBottomNavigationView() {
        assertDisplayed(R.id.bottomNavigationView)
    }

    @Test
    fun showBottomSheetDialogOnFilterButtonClick() {
        onView(withId(R.id.filterButton)).perform(click())

        onView(withText(R.string.price_low_to_high))
            .inRoot(isDialog())
            .check(matches(isDisplayed()))
        onView(withText(R.string.price_high_to_low))
            .inRoot(isDialog())
            .check(matches(isDisplayed()))
    }

    @Test
    fun displayListOfProducts() {
        assertRecyclerViewItemCount(R.id.list, 20)
        onView(allOf(withId(R.id.product_name), isDescendantOfA(nthChildOf(withId(R.id.list), 0))))
            .check(matches(withText("Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops")))
            .check(matches(isDisplayed()))
        onView(
            allOf(
                withId(R.id.product_category),
                isDescendantOfA(nthChildOf(withId(R.id.list), 0))
            )
        )
            .check(matches(withText("men's clothing")))
            .check(matches(isDisplayed()))
        onView(allOf(withId(R.id.price), isDescendantOfA(nthChildOf(withId(R.id.list), 0))))
            .check(matches(withText("$ 109.95")))
            .check(matches(isDisplayed()))
    }

    @Test
    fun performClickOnHighToLowFilter() {
        onView(withId(R.id.filterButton)).perform(click())
        onView(withId(R.id.priceHighToLow)).perform(click())
    }

    @Test
    fun performClickOnLowToHighFilter() {
        onView(withId(R.id.filterButton)).perform(click())
        onView(withId(R.id.priceLowToHigh)).perform(click())
    }

    @Test
    fun performClickOnResetFilter() {
        onView(withId(R.id.filterButton)).perform(click())
        onView(withId(R.id.resetFilters)).perform(click())
    }

    @Test
    fun displayProductDetailsFragment(){
        // The "fragmentArgs" arguments are optional.
        val fragmentArgs = bundleOf("productId" to "1")
       val scenario =  launchFragmentInHiltContainer<ProductDetailsFragment>(fragmentArgs)
        onView(withId(R.id.title)).check(matches(isDisplayed()))
        onView(withId(R.id.category)).check(matches(isDisplayed()))
        onView(withId(R.id.description)).check(matches(isDisplayed()))
        onView(withId(R.id.price)).check(matches(isDisplayed()))
    }

//
//    @Test
//    fun displayProductDetailsActionbarTitle() {
//        assertDisplayed("Details")
//    }

    fun nthChildOf(parentMatcher: Matcher<View>, childPosition: Int): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("position $childPosition of parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                if (view.parent !is ViewGroup) return false
                val parent = view.parent as ViewGroup

                return (parentMatcher.matches(parent)
                        && parent.childCount > childPosition
                        && parent.getChildAt(childPosition) == view)
            }
        }
    }

}