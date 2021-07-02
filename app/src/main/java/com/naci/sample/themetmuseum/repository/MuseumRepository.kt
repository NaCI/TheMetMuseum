package com.naci.sample.themetmuseum.repository

import com.naci.sample.themetmuseum.data.Resource
import com.naci.sample.themetmuseum.data.model.ObjectInfo
import com.naci.sample.themetmuseum.data.model.response.ObjectsResponse
import kotlinx.coroutines.flow.Flow

interface MuseumRepository {
    fun getObjects(): Flow<Resource<ObjectsResponse>>

    fun getObjectInfo(id: Int): Flow<Resource<ObjectInfo>>
}