package com.melq.composetest

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.abs
import kotlin.random.Random

@Preview
@Composable
fun MainContentScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("数当てゲーム") }
            )
        },
        content = {
            Box(modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomCenter) {
                Column(Modifier.fillMaxSize()) {

                    MainContent()
                }
            }
        })
}

@Composable
fun MainContent() {
    val inGame = remember { mutableStateOf(false) } // ゲームの状態をもつクラスかなにかにまとめたい
    val history = remember { mutableStateOf(mutableListOf<Int>()) }
    val quesNum = remember { mutableStateOf(0) }

    val strId = remember { mutableStateOf(0) }
    val ansNum = remember { mutableStateOf(0) }

    if (!inGame.value) {
        inGame.value = true
        quesNum.value = abs(Random(System.currentTimeMillis()).nextInt() % 100)
        ansNum.value = 0
        history.value.clear()
    }

    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter) {
        Column(Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {

            Column(horizontalAlignment = Alignment.End) {
                Text(text = quesNum.value.toString())

                DisplayArea(ansNum = ansNum.value) {
                    ansNum.value /= 10
                }

                Spacer(modifier = Modifier.size(24.dp))

                NumPad() {
                    if (it == 100) {
                        history.value.add(ansNum.value)
                        if (ansNum.value == quesNum.value)
                            strId.value = R.string.correct
                        else {
                            strId.value = 0
                            if (ansNum.value < quesNum.value)
                                strId.value = R.string.more
                            else
                                strId.value = R.string.less
                        }
                    } else
                        when (val newNum = ansNum.value * 10 + it) {
                            in 0..99 -> ansNum.value = newNum
                            else -> ansNum.value = it
                        }
                }
            }
        }
        if (strId.value != 0) {
            Snackbar(modifier = Modifier.padding(8.dp),
                action = {
                    if (strId.value == R.string.correct) {
                        Button(onClick = {
                            strId.value = 0
                            inGame.value = false
                        }) {
                            Text(text = "Next")
                        }
                    } else
                        Button(onClick = { strId.value = 0 }) {
                            Text(text = "OK")
                        }
                }) {
                Text(text = stringResource(id = strId.value) + " (回答数: ${history.value.size})")
            }
        }
    }
}

@Composable
fun DisplayArea(ansNum: Int, onDeleteClick: () -> Unit) {
    Row(modifier = Modifier
        .height(IntrinsicSize.Min)
        .width(IntrinsicSize.Min),
        verticalAlignment = Alignment.CenterVertically) {

        Text(text = ansNum.toString(),
            fontSize = 40.sp,
            modifier = Modifier
                .weight(2.1f)
                .fillMaxHeight()
                .border(2.dp, Color.Gray, shape = RectangleShape)
                .padding(6.dp)
                .wrapContentWidth(Alignment.End))

        ButtonSpacer()

        Button(onClick = onDeleteClick,
            modifier = Modifier
                .weight(1f)
                .width(100.dp)
                .height(72.dp),
            colors = ButtonDefaults.textButtonColors(backgroundColor = Color(175, 121, 121, 255))) {
            Image(painter = painterResource(id = R.drawable.ic_baseline_keyboard_backspace_24), contentDescription = "backSpace")
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
                    111,
                    162,
                    112,
                    255
                ), contentColor = Color.White
                )) {
                Text(text = "OK", fontSize = numFontSize)
            }
        }
    }
}

@Composable fun ButtonSpacer() { Spacer(modifier = Modifier.size(16.dp)) }