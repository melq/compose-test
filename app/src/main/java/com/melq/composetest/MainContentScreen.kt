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

@Composable
fun MainContentScreen() {
    Box(Modifier.fillMaxSize()) {
        MainContent()
    }
}

@Preview
@Composable
fun MainContent() {
    Column(modifier = Modifier.background(Color.LightGray),
        horizontalAlignment = Alignment.End) {

        @Composable fun buttonSpacer() { Spacer(modifier = Modifier.size(8.dp)) }

        Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .height(IntrinsicSize.Min)
            .width(IntrinsicSize.Min)) {
            Text(text = "test",
                modifier = Modifier
                    .weight(2f)
                    .fillMaxHeight()
                    .border(2.dp, Color.Gray, shape = RectangleShape)
                    .padding(8.dp)
                    .wrapContentWidth(Alignment.End))

            buttonSpacer()

            Button(onClick = {}, modifier = Modifier.weight(1f)) {
                Image(painter = painterResource(id = R.drawable.ic_baseline_keyboard_backspace_24), contentDescription = "backSpace")
            }
        }

        Spacer(modifier = Modifier.size(12.dp))

        Column(horizontalAlignment = Alignment.End) {
            val numRow = 3
            for (i in 0..6 step numRow) {
                Row() {
                    for (j in 1 until numRow + 1) {
                        Button(onClick = {}) {
                            Text(text = (i + j).toString())
                        }
                        if (j != numRow) buttonSpacer()
                    }
                }
                buttonSpacer()
            }
            Row() {
                Button(onClick = {}) {
                    Text(text = "0")
                }
                buttonSpacer()
                Button(onClick = {}) {
                    Text(text = "OK")
                }
            }
        }
    }
}