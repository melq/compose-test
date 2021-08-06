package com.melq.composetest

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun MainContentScreen() {
    Column(Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        MainContent()
    }
}

@Composable
fun MainContent() {
    val ansNum = remember { mutableStateOf(0) }

    Column(horizontalAlignment = Alignment.End) {

        Row(modifier = Modifier
            .height(IntrinsicSize.Min)
            .width(IntrinsicSize.Min),
            verticalAlignment = Alignment.CenterVertically) {
            Text(text = ansNum.value.toString(),
                fontSize = 40.sp,
                modifier = Modifier
                    .weight(2.1f)
                    .fillMaxHeight()
                    .border(2.dp, Color.Gray, shape = RectangleShape)
                    .padding(6.dp)
                    .wrapContentWidth(Alignment.End))

            ButtonSpacer()

            Button(onClick = { ansNum.value /= 10},
                modifier = Modifier
                    .weight(1f)
                    .width(100.dp)
                    .height(72.dp),
                colors = ButtonDefaults.textButtonColors(backgroundColor = Color(
                    131, 78, 78, 255))) {
                Image(painter = painterResource(id = R.drawable.ic_baseline_keyboard_backspace_24), contentDescription = "backSpace")
            }
        }

        Spacer(modifier = Modifier.size(24.dp))

        NumPad() {
            val newNum = ansNum.value * 10 + it
            if (newNum in 0..99)
                ansNum.value = newNum
        }
    }
}

@Composable
fun NumPad(onClick: (Int) -> Unit) {
    val numFontSize = 24.sp
    Column(horizontalAlignment = Alignment.End) {
        val numRow = 3
        for (i in 0..6 step numRow) {
            Row() {
                for (j in 1 until numRow + 1) {
                    val num = i + j
                    Button(onClick = { onClick(num) },
                        modifier = Modifier
                            .width(100.dp)
                            .height(72.dp)) {
                        Text(text = num.toString(), fontSize = numFontSize)
                    }
                    if (j != numRow) ButtonSpacer()
                }
            }
            ButtonSpacer()
        }
        Row() {
            Button(onClick = { onClick(0) }, modifier = Modifier
                .width(100.dp)
                .height(72.dp)) {
                Text(text = "0", fontSize = numFontSize)
            }
            ButtonSpacer()
            Button(onClick = { onClick(100) },
                modifier = Modifier
                    .width(100.dp)
                    .height(72.dp),
                colors = ButtonDefaults.textButtonColors(backgroundColor = Color(
                    82,
                    129,
                    83,
                    255
                ), contentColor = Color.White
                )) {
                Text(text = "OK", fontSize = numFontSize)
            }
        }
    }
}

@Composable fun ButtonSpacer() { Spacer(modifier = Modifier.size(16.dp)) }