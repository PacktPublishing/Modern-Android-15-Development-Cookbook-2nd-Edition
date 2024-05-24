package com.madona.chapter_two.app_one

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SportsScoreScreen() {
    val homeTeamScore = remember { mutableIntStateOf(0) }
    val awayTeamScore = remember { mutableIntStateOf(0) }

    LaunchedEffect(homeTeamScore.intValue) {
        // Perform some action when the home team score changes
        println("Home team score changed to ${homeTeamScore.intValue}")
    }

    LaunchedEffect(awayTeamScore.intValue) {
        // Perform some action when the away team score changes
        println("Away team score changed to ${awayTeamScore.intValue}")
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Home Team: ${homeTeamScore.intValue}")
        Button(onClick = { homeTeamScore.intValue++ }) {
            Text("Increment Home Team Score")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Away Team: ${awayTeamScore.intValue}")
        Button(onClick = { awayTeamScore.intValue++ }) {
            Text("Increment Away Team Score")
        }
    }
}

@Composable
fun SportsApp() {
    val homeTeamScore = remember { mutableIntStateOf(0) }
    val awayTeamScore = remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ScoreDisplay(homeTeamScore.intValue, awayTeamScore.intValue)
        ScoreButtons(
            onIncrementHome = { homeTeamScore.intValue++ },
            onIncrementAway = { awayTeamScore.intValue++ }
        )
    }
}

@Composable
fun ScoreDisplay(homeScore: Int, awayScore: Int) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Home Team: $homeScore")
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Away Team: $awayScore")
    }
}

@Composable
fun ScoreButtons(onIncrementHome: () -> Unit, onIncrementAway: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = onIncrementHome) {
            Text("Increment Home Team Score")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onIncrementAway) {
            Text("Increment Away Team Score")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShowSportApp() {
    SportsScoreScreen()
}
