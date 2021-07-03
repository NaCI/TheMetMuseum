package com.naci.sample.themetmuseum.repository

import com.naci.sample.themetmuseum.api.MetMuseumService
import com.naci.sample.themetmuseum.common.BaseRepository
import com.naci.sample.themetmuseum.data.Resource
import com.naci.sample.themetmuseum.data.mapper.ObjectInfoResponseToDomainObjectInfo
import com.naci.sample.themetmuseum.data.model.ObjectInfo
import com.naci.sample.themetmuseum.data.model.response.ObjectsResponse
import com.naci.sample.themetmuseum.data.serviceRequestFlow
import com.naci.sample.themetmuseum.data.serviceRequestTransformFlow
import com.naci.sample.themetmuseum.data.serviceRequestTransformFlowForTest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MuseumRepositoryImp @Inject constructor(
    private val metMuseumService: MetMuseumService
) : MuseumRepository, BaseRepository() {
    override fun getObjects(): Flow<Resource<ObjectsResponse>> {
        return serviceRequestFlow {
            metMuseumService.objects()
        }
    }

    override fun getObjectInfo(id: Int): Flow<Resource<ObjectInfo>> {
        return serviceRequestTransformFlow(
            suspendFun = {
                metMuseumService.objectInfo(id)
            }, transform = { objectInfoResponse ->
                ObjectInfoResponseToDomainObjectInfo().map(objectInfoResponse)
            }
        )
    }

    override fun getObjectInfoWithDelay(id: Int, delayInMillis: Long): Flow<Resource<ObjectInfo>> {
        return serviceRequestTransformFlowForTest(
            suspendFun = {
                metMuseumService.objectInfo(id)
            }, transform = { objectInfoResponse ->
                ObjectInfoResponseToDomainObjectInfo().map(objectInfoResponse)
            }, delayInMillis = delayInMillis
        )
    }

}