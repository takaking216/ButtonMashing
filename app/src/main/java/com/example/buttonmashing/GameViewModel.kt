package com.example.buttonmashing

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class GameViewModel: ViewModel() {
    val count = MutableLiveData<Int>(0)
    val time = MutableLiveData<String>("00.00")

    fun onClick() {
        count.value = (count.value ?: 0) + 1
    }

    private fun timer(periodMs: Long, endTimeMs: Long): Flow<Long> = flow<Long> {
        var currentTime: Long = 0

        while (currentTime <= endTimeMs) {
            emit(currentTime)
            delay(periodMs)
            currentTime += periodMs
        }
    }

    fun start() {
        viewModelScope.launch {
            timer(16, 10_000).collect {
                val second = it / 1000
                val millSecond = it % 1000 / 10
                time.value = "$second.$millSecond"
            }
        }
    }
}