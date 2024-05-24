package com.madona.composeui.app_one

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FlightBookingScreen() {
    var departureCity by remember { mutableStateOf("") }
    var destinationCity by remember { mutableStateOf("") }
    var departureDate by remember { mutableStateOf("") }
    var passengers by remember { mutableIntStateOf(1) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Departure city field
        TextField(
            value = departureCity,
            onValueChange = { departureCity = it },
            label = { Text("Departure City") }
        )

        // Destination city field
        TextField(
            value = destinationCity,
            onValueChange = { destinationCity = it },
            label = { Text("Destination City") }
        )

        // Departure date field
        TextField(
            value = departureDate,
            onValueChange = { departureDate = it },
            label = { Text("Departure Date") }
        )


        // Number of passengers field
        Text("Passengers: $passengers")
        Row(verticalAlignment = Alignment.CenterVertically) {
            Button(onClick = { if (passengers > 1) passengers-- }) {
                Text("-")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text("$passengers")
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { passengers++ }) {
                Text("+")
            }
        }

        // Book flight button
        Button(
            onClick = { bookFlight(departureCity, destinationCity, departureDate, passengers) },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Book Flight")
        }
    }

}

private fun bookFlight(
    departureCity: String,
    destinationCity: String,
    departureDate: String,
    passengers: Int
) {

    println("Booking flight from $departureCity to $destinationCity on $departureDate")
    println("Number of passengers: $passengers")
}

@Preview(showBackground = true)
@Composable
fun ShowFlightBookingApp(){
    FlightBookingScreen()
}