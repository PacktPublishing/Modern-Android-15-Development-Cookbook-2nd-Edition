package com.madona.composeui.app_two

import android.graphics.Paint
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Canvas
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun HistogramChartScreen() {
    var babyNames by remember { mutableStateOf(listOf("", "", "", "", "")) }
    var showHistogram by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Input fields for baby names
        for (i in babyNames.indices) {
            TextField(
                value = babyNames[i],
                onValueChange = { newValue ->
                    babyNames = babyNames.toMutableList().apply { set(i, newValue) }
                },
                label = { Text("Baby Name ${i + 1}") },
                modifier = Modifier.fillMaxWidth()
            )
        }

        // Button to display histogram
        Button(
            onClick = { showHistogram = true },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Show Histogram")
        }

        // Histogram display
        if (showHistogram) {
            HistogramChart(babyNames)
        }
    }
}

@Composable
fun HistogramChart(names: List<String>) {
    // Count occurrence of each name
    val nameCounts = names.groupingBy { it }.eachCount()

    // Find the maximum count
    val maxCount = nameCounts.values.maxOrNull() ?: 0

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .testTag("HistogramChart")
            .padding(horizontal = 16.dp)
    ) {
        // Draw histogram bars
        nameCounts.entries.forEachIndexed { index, entry ->
            val (name, count) = entry
            val barWidth = size.width / (names.size + 1)
            val barHeight = (count.toFloat() / maxCount) * size.height
            val startX = (index + 1) * barWidth

            // Draw histogram bar
            drawRect(
                color = Color.Blue,
                topLeft = Offset(startX - barWidth / 2, size.height - barHeight),
                size = Size(barWidth, barHeight),
                style = Stroke(1.dp.toPx())
            )

            // Draw text below histogram bar
            drawContext.canvas.nativeCanvas.drawText(
                name,
                startX - barWidth / 2,
                size.height + 16.dp.toPx(),
                Paint().apply {
                    color = Color.Black.toArgb()
                    textSize = 12.sp.toPx()
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShowHistogramChartApp(){
   HistogramChartScreen()
}