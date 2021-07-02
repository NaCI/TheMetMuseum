package com.naci.sample.themetmuseum.data.mapper

import com.naci.sample.themetmuseum.data.model.ObjectInfo
import com.naci.sample.themetmuseum.data.model.response.ObjectInfoResponse

interface Mapper<I, O> {
    fun map(input: I): O
}

class ObjectInfoResponseToDomainObjectInfo : Mapper<ObjectInfoResponse, ObjectInfo> {
    override fun map(input: ObjectInfoResponse): ObjectInfo {
        return ObjectInfo(
            input.artistDisplayName,
            input.city,
            input.country,
            input.culture,
            input.excavation,
            input.linkResource,
            input.objectID,
            input.objectName,
            input.objectURL,
            input.objectWikidata_URL,
            input.period,
            input.primaryImage,
            input.primaryImageSmall,
            input.region,
            input.reign,
            input.title
        )
    }
}