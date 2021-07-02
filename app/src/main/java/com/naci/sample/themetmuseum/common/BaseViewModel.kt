package com.naci.sample.themetmuseum.common

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

abstract class BaseViewModel : ViewModel() {
    private val _inProgressCount = MutableStateFlow(0)

    // TODO : This must be StateFlow
    val progressState: Flow<Boolean>
        get() = _inProgressCount.map { progressCount ->
            progressCount > 0
        }

    fun increaseInProgressCount() {
        _inProgressCount.value = _inProgressCount.value.plus(1)
    }

    fun decreaseInProgressCount() {
        _inProgressCount.value = _inProgressCount.value.minus(1)
    }

}