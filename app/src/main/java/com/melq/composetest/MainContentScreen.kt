package com.melq.composetest

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
    Column(horizontalAlignment = Alignment.End) {

        @Composable fun buttonSpacer() { Spacer(modifier = Modifier.size(16.dp)) }
        val numFontSize = 24.sp

        Row(modifier = Modifier
            .height(IntrinsicSize.Min)
            .width(IntrinsicSize.Min),
            verticalAlignment = Alignment.CenterVertically) {
            Text(text = "test",
                fontSize = 40.sp,
                modifier = Modifier
                    .weight(2.1f)
                    .fillMaxHeight()
                    .border(2.dp, Color.Gray, shape = RectangleShape)
                    .padding(6.dp)
                    .wrapContentWidth(Alignment.End))

            buttonSpacer()

            Button(onClick = {},
                modifier = Modifier
                    .weight(1f)
                    .width(100.dp).height(72.dp),
                colors = ButtonDefaults.textButtonColors(backgroundColor = Color(
                    131, 78, 78, 255))) {
                Image(painter = painterResource(id = R.drawable.ic_baseline_keyboard_backspace_24), contentDescription = "backSpace")
            }
        }

        Spacer(modifier = Modifier.size(24.dp))

        Column(horizontalAlignment = Alignment.End) {
            val numRow = 3
            for (i in 0..6 step numRow) {
                Row() {
                    for (j in 1 until numRow + 1) {
                        Button(onClick = {}, modifier = Modifier
                            .width(100.dp)
                            .height(72.dp)) {
                            Text(text = (i + j).toString(), fontSize = numFontSize)
                        }
                        if (j != numRow) buttonSpacer()
                    }
                }
                buttonSpacer()
            }
            Row() {
                Button(onClick = {}, modifier = Modifier
                    .width(100.dp)
                    .height(72.dp)) {
                    Text(text = "0", fontSize = numFontSize)
                }
                buttonSpacer()
                Button(onClick = {},
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
}