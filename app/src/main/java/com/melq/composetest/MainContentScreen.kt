package com.melq.composetest

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
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
    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        @Composable fun buttonSpacer() { Spacer(modifier = Modifier.size(16.dp)) }
        val numFontSize = 24.sp

        Row(modifier = Modifier
            .height(IntrinsicSize.Min)
            .width(IntrinsicSize.Min),
            verticalAlignment = Alignment.CenterVertically) {
            Text(text = "test",
                fontSize = 32.sp,
                modifier = Modifier
                    .weight(3f)
                    .fillMaxHeight()
                    .border(2.dp, Color.Gray, shape = RectangleShape)
                    .padding(6.dp)
                    .wrapContentWidth(Alignment.End))

            buttonSpacer()

            Button(onClick = {}, modifier = Modifier.weight(1f)) {
                Image(painter = painterResource(id = R.drawable.ic_baseline_keyboard_backspace_24), contentDescription = "backSpace")
            }
        }

        Spacer(modifier = Modifier.size(24.dp))

        Column(horizontalAlignment = Alignment.End) {
            val numRow = 3
            for (i in 0..6 step numRow) {
                Row() {
                    for (j in 1 until numRow + 1) {
                        Button(onClick = {}, modifier = Modifier.width(100.dp).height(72.dp)) {
                            Text(text = (i + j).toString(), fontSize = numFontSize)
                        }
                        if (j != numRow) buttonSpacer()
                    }
                }
                buttonSpacer()
            }
            Row() {
                Button(onClick = {}, modifier = Modifier.width(100.dp).height(72.dp)) {
                    Text(text = "0", fontSize = numFontSize)
                }
                buttonSpacer()
                Button(onClick = {}, modifier = Modifier.width(100.dp).height(72.dp)) {
                    Text(text = "OK", fontSize = numFontSize)
                }
            }
        }
    }
}