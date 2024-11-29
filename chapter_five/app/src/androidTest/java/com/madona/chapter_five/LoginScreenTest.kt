package com.madona.chapter_five

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

// LoginScreenTest.kt
@ExperimentalCoroutinesApi
class LoginScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var viewModel: LoginViewModel
    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = LoginViewModel()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun validInput_showsSuccessMessage() {
        composeTestRule.setContent {
            LoginScreen(viewModel = viewModel)
        }

        composeTestRule.onNodeWithTag("Username").performTextInput("testuser")
        composeTestRule.onNodeWithTag("Password").performTextInput("password")
        composeTestRule.onNodeWithTag("Login").performClick()

        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithTag("Login Successful").assertExists()
    }

    @Test
    fun invalidInput_showsErrorMessage() {
        composeTestRule.setContent {
            LoginScreen(viewModel = viewModel)
        }

        composeTestRule.onNodeWithTag("Username").performTextInput("wronguser")
        composeTestRule.onNodeWithTag("Password").performTextInput("wrongpassword")
        composeTestRule.onNodeWithTag("Login").performClick()

        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithTag("Invalid Credentials").assertExists()
    }
}
