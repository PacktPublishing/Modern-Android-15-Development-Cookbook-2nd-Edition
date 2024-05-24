package com.madona.chapter_two.app_two

import androidx.compose.ui.graphics.Path
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun AnimationScreen() {
    // Obtain the ViewModel
    val viewModel: AnimationViewModel = viewModel()

    val isRotated by viewModel.isRotated.collectAsState()
    val rotationAngle by animateFloatAsState(targetValue = if (isRotated) 360f else 0f, label = "")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Triangle canvas
        Box(
            modifier = Modifier
                .size(200.dp)
                .rotate(rotationAngle)
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                drawTriangle()
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Button to trigger animation
        Button(
            onClick = { viewModel.toggleRotation() },
            shape = CircleShape
        ) {
            Text("Animate Triangle")
        }
    }
}

private fun DrawScope.drawTriangle() {
    val path = Path().apply {
        moveTo(size.width / 2, 0f)
        lineTo(size.width, size.height)
        lineTo(0f, size.height)
        close()
    }
    drawPath(path, color = Color.Blue)
}

@Preview(showBackground = true)
@Composable
fun ShowAnimationScreen() {
    AnimationScreen()
}