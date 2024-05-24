package com.madona.composeui.tests

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.madona.composeui.app_one.FlightBookingScreen
import junit.framework.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class FlightBookingScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testFlightBookingScreen() {

        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))

        // Launch FlightBookingScreen
        composeTestRule.setContent {
            FlightBookingScreen()
        }

        // Test input fields
        composeTestRule
            .onNodeWithText("Departure City")
            .performTextInput("New York")
        composeTestRule
            .onNodeWithText("Destination City")
            .performTextInput("Singapore")
        composeTestRule
            .onNodeWithText("Departure Date")
            .performTextInput("05-13-2024")

        // Test passengers counter
        composeTestRule
            .onNodeWithText("-", ignoreCase = true)
            .performClick() // Decrement passengers
        composeTestRule
            .onNodeWithText("+", ignoreCase = true)
            .performClick() // Increment passengers

        // Test book flight button
        composeTestRule
            .onNodeWithText("Book Flight")
            .performClick()

        // Get captured output from the ByteArrayOutputStream
        val capturedOutput = outputStream.toString().trim()

        // Define expected messages
        val expectedOutput = """
        Booking flight from New York to Singapore on 05-13-2024
        Number of passengers: 2
    """.trimIndent()

        // Assert that captured output matches expected messages
        assertEquals(expectedOutput, capturedOutput)
    }
}
