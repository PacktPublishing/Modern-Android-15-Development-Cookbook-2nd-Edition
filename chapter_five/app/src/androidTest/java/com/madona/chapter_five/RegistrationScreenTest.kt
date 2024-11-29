package com.madona.chapter_five

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RegistrationScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun validInput_showsSuccessMessage() {
        val viewModel = RegistrationViewModel()

        composeTestRule.setContent {
            RegistrationScreen(viewModel = viewModel)
        }

        composeTestRule.onNodeWithTag("Username").performTextInput("testuser")
        composeTestRule.onNodeWithTag("Email").performTextInput("invalid-email")
        composeTestRule.onNodeWithTag("Register").performClick()

        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithTag("Registration Successful").assertExists()
    }

    @Test
    fun invalidEmail_showsErrorMessage() {
        val viewModel = RegistrationViewModel()

        composeTestRule.setContent {
            RegistrationScreen(viewModel = viewModel)
        }

        composeTestRule.onNodeWithTag("Username").performTextInput("testuser")
        composeTestRule.onNodeWithTag("Email").performTextInput("invalid-email")
        composeTestRule.onNodeWithTag("Register").performClick()

        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithTag("Invalid input").assertExists()
    }
}
