package com.challenge.weatherapplhm.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Colors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun TextDefault(
    modifier: Modifier,
    title: String,
    textSize: Int,
    fontStyle: FontWeight,
    textColor: Color
) {

    Text(
        modifier = modifier,
        text = title,
        fontWeight = fontStyle,
        color = textColor,
        fontSize = textSize.sp
    )

}