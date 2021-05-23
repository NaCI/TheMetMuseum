package com.naci.sample.themetmuseum.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class BaseViewModel : ViewModel() {
    private val _inProgressCount = MutableLiveData<Int>()

    val progress: LiveData<Boolean> = Transformations.switchMap(_inProgressCount) { progressCount ->
        MutableLiveData(progressCount > 0)
    }

    fun increaseInProgressCount() {
        _inProgressCount.value = _inProgressCount.value?.plus(1) ?: 0
    }

    fun decreaseInProgressCount() {
        _inProgressCount.value = _inProgressCount.value?.minus(1) ?: 0
    }

}