package com.example.buttonmashing

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainSharedViewModel : ViewModel() {
    val history = MutableLiveData<MutableList<Int>>(mutableListOf())
}