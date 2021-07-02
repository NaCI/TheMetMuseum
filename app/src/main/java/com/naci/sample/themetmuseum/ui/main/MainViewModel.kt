package com.naci.sample.themetmuseum.ui.main

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.naci.sample.themetmuseum.common.BaseViewModel
import com.naci.sample.themetmuseum.data.Resource
import com.naci.sample.themetmuseum.data.model.ObjectInfo
import com.naci.sample.themetmuseum.data.model.response.ObjectsResponse
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

    private val _uiState = MutableStateFlow<Resource<List<ObjectInfo>>>(Resource.Empty)
    val uiState: StateFlow<Resource<List<ObjectInfo>>> get() = _uiState.asStateFlow()

    private val _uiStateAllObjects = MutableStateFlow<Resource<ObjectsResponse>>(Resource.Empty)
    val uiStateAllObjects: StateFlow<Resource<ObjectsResponse>> get() = _uiStateAllObjects.asStateFlow()

    private val objectsList = mutableListOf<ObjectInfo>()

    fun getAllObjects() {
        viewModelScope.launch {
            museumRepository.getObjects().collect { resource ->
                _uiStateAllObjects.value = resource
            }
        }
    }

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
                } else if (resource is Resource.Success) {
                    _uiState.value = handleNewObject(resource)
                } else if (resource is Resource.Error) {
                    _uiState.value = resource
                }
            }
        }
    }

    private fun handleNewObject(resource: Resource.Success<ObjectInfo>): Resource<List<ObjectInfo>> {
        objectsList.add(resource.data)
        return Resource.Success(objectsList)
    }

    fun clearObjectList() {
        objectsList.clear()
    }
}