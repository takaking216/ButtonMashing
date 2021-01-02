package com.example.buttonmashing

import androidx.lifecycle.MutableLiveData

class GameViewModel {
    val count = MutableLiveData<Int>(0)
    val countText = MutableLiveData<String>("0回")

    fun onClick() {
        count.value = (count.value ?: 0) + 1
    }
}