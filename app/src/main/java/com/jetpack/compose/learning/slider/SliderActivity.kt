package com.jetpack.compose.learning.slider

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class SliderActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SliderExamples()
        }
    }

    @Preview
    @Composable
    fun SliderExamples() {
        val headerStyle = MaterialTheme.typography.h6
        val spaceHeight = 24.dp
        Column {
            TopAppBar(
                title = { Text("Sliders") },
                navigationIcon = {
                    IconButton(onClick = { onBackPressed() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = null)
                    }
                }
            )
            Column(Modifier.padding(20.dp)) {
                Text(text = "Continuous Slider", style = headerStyle)
                SliderSample()
                Spacer(Modifier.requiredHeight(spaceHeight))
                Text(text = "Discrete Slider with custom color", style = headerStyle)
                StepsSliderSample()
                Spacer(Modifier.requiredHeight(spaceHeight))
                Text(text = "Range Continuous Slider", style = headerStyle)
                RangeSliderSample()
                Spacer(Modifier.requiredHeight(spaceHeight))
                Text(text = "Range Discrete Slider", style = headerStyle)
                StepRangeSliderSample()
            }
        }
    }

    @Composable
    fun SliderSample() {
        var sliderPosition by remember { mutableStateOf(0f) }
        Text(text = "Slider value =  $sliderPosition", fontSize = 16.sp)
        Slider(value = sliderPosition, onValueChange = { sliderPosition = it })
    }

    @Composable
    fun StepsSliderSample() {
        var sliderPosition by remember { mutableStateOf(0f) }
        Text(text = "Slider value =  $sliderPosition", fontSize = 16.sp)
        Slider(
            value = sliderPosition,
            onValueChange = { sliderPosition = it },
            valueRange = 0f..100f,
            onValueChangeFinished = {
                // some business logic update with the state you hold
            },
            steps = 5,
            colors = SliderDefaults.colors(
                thumbColor = MaterialTheme.colors.secondary,
                activeTrackColor = MaterialTheme.colors.secondary
            )
        )
    }

    @Composable
    @OptIn(ExperimentalMaterialApi::class)
    fun RangeSliderSample() {
        var sliderPosition by remember { mutableStateOf(0f..100f) }
        Text(
            text = "From range  ${sliderPosition.start}  to  ${sliderPosition.endInclusive}",
            fontSize = 16.sp
        )
        RangeSlider(
            values = sliderPosition,
            onValueChange = { it -> sliderPosition = it },
            valueRange = 0f..100f,
            onValueChangeFinished = {
                // some business logic update with the state you hold
            },
        )
    }

    @Composable
    @OptIn(ExperimentalMaterialApi::class)
    fun StepRangeSliderSample() {
        var sliderPosition by remember { mutableStateOf(0f..100f) }
        Text(
            text = "From range  ${sliderPosition.start}  to  ${sliderPosition.endInclusive}",
            fontSize = 16.sp
        )
        RangeSlider(
            steps = 5,
            values = sliderPosition,
            onValueChange = { it -> sliderPosition = it },
            valueRange = 0f..100f,
            onValueChangeFinished = {
                // some business logic update with the state you hold
            },
            colors = SliderDefaults.colors(
                thumbColor = MaterialTheme.colors.secondary,
                activeTrackColor = MaterialTheme.colors.secondary
            )
        )
    }
}