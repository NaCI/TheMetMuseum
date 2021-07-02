package com.naci.sample.themetmuseum.api

import com.naci.sample.themetmuseum.data.model.response.ObjectInfoResponse
import com.naci.sample.themetmuseum.data.model.response.ObjectsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MetMuseumService {

    @GET("objects")
    suspend fun objects(): ObjectsResponse

    @GET("objects/{objectID}")
    suspend fun objectInfo(@Path("objectID") id: Int): ObjectInfoResponse
}