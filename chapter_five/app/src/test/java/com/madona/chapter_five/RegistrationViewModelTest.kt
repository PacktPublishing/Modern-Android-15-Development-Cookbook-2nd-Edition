package com.madona.chapter_five

import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test


@ExperimentalCoroutinesApi
class RegistrationViewModelTest {

    private lateinit var viewModel: RegistrationViewModel
    private val testDispatcher =  StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = RegistrationViewModel()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
    }


    @Test
    fun validInput_registersSuccessfully() = runTest {
        viewModel.onUsernameChange("testuser")
        viewModel.onEmailChange("test@example.com")

        viewModel.register()

        advanceTimeBy(2000) // Advance the virtual time by the delay duration
        advanceUntilIdle() // Ensure all coroutines complete

        assertEquals("Registration Successful", viewModel.message.value)
    }

    @Test
    fun invalidEmail_showsErrorMessage() = runTest {
        viewModel.onUsernameChange("testuser")
        viewModel.onEmailChange("invalid-email")

        viewModel.register()

        advanceTimeBy(2000) // Advance the virtual time by the delay duration
        advanceUntilIdle() // Ensure all coroutines complete

        assertEquals("Invalid input", viewModel.message.value)
    }


}