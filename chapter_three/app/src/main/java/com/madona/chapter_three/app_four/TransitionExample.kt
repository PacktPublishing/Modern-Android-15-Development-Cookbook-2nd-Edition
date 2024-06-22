package com.madona.chapter_three.app_four

import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun TransitionApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "list") {
        composable("list") { ListScreen(navController) }
        composable(
            "detail/{itemId}",
            arguments = listOf(navArgument("itemId") { type = NavType.StringType })
        ) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getString("itemId")
            DetailScreen(navController, itemId)
        }
    }
}


@Composable
fun ListScreen(navController: NavHostController) {
    val items = listOf("Item 1", "Item 2", "Item 3")
    LazyColumn {
        items(items) { item ->
            Text(
                text = item,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clickable {
                        navController.navigate("detail/$item")
                    }
            )
        }
    }
}

@Composable
fun DetailScreen(navController: NavHostController, itemId: String?) {
    val transitionState = remember {
        MutableTransitionState(false).apply { targetState = true }
    }
    val transition = updateTransition(transitionState, label = "Detail Transition")

    val alpha by transition.animateFloat(
        transitionSpec = { tween(durationMillis = 1000) },
        label = "Alpha Animation"
    ) { state ->
        if (state) 1f else 0f
    }

    val offset by transition.animateDp(
        transitionSpec = { spring(dampingRatio = Spring.DampingRatioMediumBouncy) },
        label = "Offset Animation"
    ) { state ->
        if (state) 0.dp else 100.dp
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .alpha(alpha)
            .offset(y = offset)
            .background(Color.Gray, shape = RoundedCornerShape(8.dp))
    ){
        Text(
            text = "Detail Screen for item: $itemId",
            fontSize = 24.sp
        )
        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier.padding(top = 32.dp)
        ) {
            Text(text = "Go Back")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShowTransitionScreen() {
    TransitionApp()
}

