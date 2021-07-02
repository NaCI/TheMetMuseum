package com.naci.sample.themetmuseum.ui.main

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.naci.sample.themetmuseum.common.BaseViewModel
import com.naci.sample.themetmuseum.data.Resource
import com.naci.sample.themetmuseum.data.model.ObjectInfo
import com.naci.sample.themetmuseum.repository.MuseumRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val museumRepository: MuseumRepository,
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    private val _uiState = MutableStateFlow<Resource<ObjectInfo>>(Resource.Empty)
    val uiState: StateFlow<Resource<ObjectInfo>> get() = _uiState.asStateFlow()

    fun getObjectInfo(
        id: Int
    ) {
        viewModelScope.launch {
            museumRepository.getObjectInfo(id).collect { resource ->
                if (resource is Resource.Loading) {
                    if (resource.isLoading) {
                        increaseInProgressCount()
                    } else {
                        decreaseInProgressCount()
                    }
                } else {
                    _uiState.value = resource
                }
            }
        }
    }
}