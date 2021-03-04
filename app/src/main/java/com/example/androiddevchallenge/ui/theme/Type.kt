package com.example.androiddevchallenge.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.example.androiddevchallenge.R

private val rainyHearts = FontFamily(
    Font(R.font.rainyhearts)
)


// Set of Material typography styles to start with
val Typography = Typography(
    defaultFontFamily = rainyHearts,
//    body1 = TextStyle(
//        fontFamily = rainyHearts,
//        fontWeight = FontWeight.Normal,
//        fontSize = 16.sp
//    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)