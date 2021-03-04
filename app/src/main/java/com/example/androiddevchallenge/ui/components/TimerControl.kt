package com.example.androiddevchallenge.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import java.util.*

@Composable
fun TimerControl(text: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(
        onClick = onClick,
        colors =  ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
        modifier = modifier
            .padding(8.dp)
            .border(2.dp, MaterialTheme.colors.onBackground, shape = RoundedCornerShape(8.dp))
    ) {
        Text(
            text = text.toUpperCase(Locale.getDefault()),
            style = MaterialTheme.typography.button
        )
    }
}
