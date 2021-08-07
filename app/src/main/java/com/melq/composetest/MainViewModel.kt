package com.melq.composetest

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    val ansNum = MutableLiveData(0)
    val snackbarVisibleState = MutableLiveData(false)

    fun onNumChange(newNum: Int) {
        ansNum.value = newNum
    }
    fun onSnackbarVisibleStateChange(iSVisible: Boolean) {
        snackbarVisibleState.value = iSVisible
    }
}