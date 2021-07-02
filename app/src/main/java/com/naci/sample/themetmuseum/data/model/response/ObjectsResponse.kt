package com.naci.sample.themetmuseum.data.model.response

import com.google.gson.annotations.SerializedName

data class ObjectsResponse(
    @field:SerializedName("total") val total: Int,
    @field:SerializedName("objectIDs") val objectIds: List<Int>
)
