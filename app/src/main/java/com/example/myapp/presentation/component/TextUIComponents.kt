package com.example.myapp.presentation.component

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun BlackText(text:String = ""){
    Text(
        text = text,
        color = Color.Black,
        fontSize = 18.sp,
        fontWeight = FontWeight.Medium,
    )
}

@Composable
fun LargeBlackText(text:String = ""){
    Text(
        text = text,
        color = Color.Black,
        fontSize = 60.sp,
        fontWeight = FontWeight.Medium,
    )
}

@Composable
fun DarkGrayText(text:String = ""){
    Text(
        text = text,
        color = Color.DarkGray,
        fontSize = 20.sp,
        fontWeight = FontWeight.Medium,
    )
}

@Preview
@Composable
fun PreviewSearchbar(){
    BlackText()
    DarkGrayText()
}