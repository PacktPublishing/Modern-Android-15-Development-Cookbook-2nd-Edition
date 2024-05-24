package com.madona.chapter_two.tests

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.madona.chapter_two.app_one.SportsScoreScreen
import org.junit.Rule
import org.junit.Test

class SportsScoreScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun sportsScoreScreen_initialState() {
        // Step 1: Set the content of the test
        composeTestRule.setContent {
            SportsScoreScreen()
        }

        // Step 2: Verify initial state
        composeTestRule.onNodeWithText("Home Team: 0").assertExists()
        composeTestRule.onNodeWithText("Away Team: 0").assertExists()
    }

    @Test
    fun sportsScoreScreen_incrementHomeTeamScore() {
        // Step 1: Set the content of the test
        composeTestRule.setContent {
            SportsScoreScreen()
        }

        // Step 2: Simulate button click to increment home team score
        composeTestRule.onNodeWithText("Increment Home Team Score").performClick()
        // Step 3: Verify updated state
        composeTestRule.onNodeWithText("Home Team: 1").assertExists()
    }

    @Test
    fun sportsScoreScreen_incrementAwayTeamScore() {
        // Step 1: Set the content of the test
        composeTestRule.setContent {
            SportsScoreScreen()
        }

        // Step 2: Simulate button click to increment away team score
        composeTestRule.onNodeWithText("Increment Away Team Score").performClick()
        // Step 3: Verify updated state
        composeTestRule.onNodeWithText("Away Team: 1").assertExists()
    }
}
