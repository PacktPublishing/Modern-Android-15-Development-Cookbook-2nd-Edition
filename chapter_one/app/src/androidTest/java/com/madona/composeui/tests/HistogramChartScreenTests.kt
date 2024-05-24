package com.madona.composeui.tests

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.madona.composeui.app_two.HistogramChartScreen
import org.junit.Rule
import org.junit.Test

class HistogramChartScreenTests {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testInputFieldsDisplayed() {
        composeTestRule.setContent {
            HistogramChartScreen()
        }

        // Verify input fields for baby names
        repeat(5) { index ->
            composeTestRule.onNodeWithText("Baby Name ${index + 1}").assertIsDisplayed()
        }
    }

    @Test
    fun testHistogramButtonClickable() {
        composeTestRule.setContent {
            HistogramChartScreen()
        }

        // Verify button to display histogram
        composeTestRule.onNodeWithText("Show Histogram").performClick()
    }

    @Test
    fun testHistogramDisplayed() {
        // Set up the screen to display the histogram
        composeTestRule.setContent {
            HistogramChartScreen()
        }

        // Click the button to display histogram
        composeTestRule.onNodeWithText("Show Histogram").performClick()

        // Verify histogram is displayed
        composeTestRule.onNodeWithTag("HistogramChart").assertExists()
    }
}
