package com.naci.sample.themetmuseum.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ObjectInfo(
    val artistDisplayName: String,
    val city: String,
    val country: String,
    val culture: String,
    val excavation: String,
    val linkResource: String,
    val objectID: Int,
    val objectName: String,
    val objectURL: String,
    val objectWikidata_URL: String,
    val period: String,
    val primaryImage: String,
    val primaryImageSmall: String,
    val region: String,
    val reign: String,
    val title: String
) : Parcelable