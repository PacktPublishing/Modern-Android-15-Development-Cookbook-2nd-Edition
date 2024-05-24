package com.madona.composeui.tests

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.composeui.CounterScreen
import org.junit.Rule
import org.junit.Test

class CounterScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testCounterScreen() {
        composeTestRule.setContent {
            CounterScreen()
        }

        // Assert initial counter value
        composeTestRule
            .onNodeWithText("Counter: 0")
            .assertExists()

        // Click the button to increment the counter
        composeTestRule.onNodeWithText("Increment").performClick()

        // Assert updated counter value
        composeTestRule
            .onNodeWithText("Counter: 1")
            .assertExists()
    }
}