package com.madona.chapter_three.app_three

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        // Home Screen
        composable(
            route = "home",
            deepLinks = listOf(navDeepLink { uriPattern = "app://myapp/home" })
        ) { HomeScreen(navController) }

        // Profile Screen with userId parameter
        composable(
            route = "profile/{userId}",
            arguments = listOf(navArgument("userId") { type = NavType.StringType }),
            deepLinks = listOf(navDeepLink { uriPattern = "app://myapp/profile/{userId}" })
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId")
            ProfileScreen(navController, userId)
        }

        // Settings Screen
        composable(
            route = "settings",
            deepLinks = listOf(navDeepLink { uriPattern = "app://myapp/settings" })
        ) { SettingsScreen(navController) }
    }
}

@Composable
fun HomeScreen(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Home Screen")
        Button(onClick = { navController.navigate("profile/123") }) {
            Text(text = "Go to Profile")
        }
        Button(onClick = { navController.navigate("settings") }) {
            Text(text = "Go to Settings")
        }
    }
}

@Composable
fun ProfileScreen(navController: NavHostController, userId: String?) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Profile Screen for user $userId")
        Button(onClick = { navController.navigate("home") }) {
            Text(text = "Go to Home")
        }
        Button(onClick = { navController.navigate("settings") }) {
            Text(text = "Go to Settings")
        }
    }
}

@Composable
fun SettingsScreen(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Settings Screen")
        Button(onClick = { navController.navigate("home") }) {
            Text(text = "Go to Home")
        }
        Button(onClick = { navController.navigate("profile/123") }) {
            Text(text = "Go to Profile")
        }
    }
}


