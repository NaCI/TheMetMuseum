package com.naci.sample.themetmuseum.data.model.response

import com.google.gson.annotations.SerializedName

data class ObjectInfoResponse(
    @field:SerializedName("GalleryNumber") val galleryNumber: String,
    @field:SerializedName("accessionNumber") val accessionNumber: String,
    @field:SerializedName("accessionYear") val accessionYear: String,
    @field:SerializedName("artistAlphaSort") val artistAlphaSort: String,
    @field:SerializedName("artistBeginDate") val artistBeginDate: String,
    @field:SerializedName("additionalImages") val additionalImages: List<Any>,
    @field:SerializedName("artistDisplayBio") val artistDisplayBio: String,
    @field:SerializedName("artistDisplayName") val artistDisplayName: String,
    @field:SerializedName("artistEndDate") val artistEndDate: String,
    @field:SerializedName("artistGender") val artistGender: String,
    @field:SerializedName("artistNationality") val artistNationality: String,
    @field:SerializedName("artistPrefix") val artistPrefix: String,
    @field:SerializedName("artistRole") val artistRole: String,
    @field:SerializedName("artistSuffix") val artistSuffix: String,
    @field:SerializedName("artistULAN_URL") val artistULAN_URL: String,
    @field:SerializedName("artistWikidata_URL") val artistWikidata_URL: String,
    @field:SerializedName("city") val city: String,
    @field:SerializedName("classification") val classification: String,
    @field:SerializedName("country") val country: String,
    @field:SerializedName("county") val county: String,
    @field:SerializedName("creditLine") val creditLine: String,
    @field:SerializedName("culture") val culture: String,
    @field:SerializedName("department") val department: String,
    @field:SerializedName("dimensions") val dimensions: String,
    @field:SerializedName("dynasty") val dynasty: String,
    @field:SerializedName("excavation") val excavation: String,
    @field:SerializedName("geographyType") val geographyType: String,
    @field:SerializedName("isHighlight") val isHighlight: Boolean,
    @field:SerializedName("isPublicDomain") val isPublicDomain: Boolean,
    @field:SerializedName("isTimelineWork") val isTimelineWork: Boolean,
    @field:SerializedName("linkResource") val linkResource: String,
    @field:SerializedName("locale") val locale: String,
    @field:SerializedName("locus") val locus: String,
    @field:SerializedName("measurements") val measurements: List<Measurement>,
    @field:SerializedName("medium") val medium: String,
    @field:SerializedName("metadataDate") val metadataDate: String,
    @field:SerializedName("objectBeginDate") val objectBeginDate: Int,
    @field:SerializedName("objectDate") val objectDate: String,
    @field:SerializedName("objectEndDate") val objectEndDate: Int,
    @field:SerializedName("objectID") val objectID: Int,
    @field:SerializedName("objectName") val objectName: String,
    @field:SerializedName("objectURL") val objectURL: String,
    @field:SerializedName("objectWikidata_URL") val objectWikidata_URL: String,
    @field:SerializedName("period") val period: String,
    @field:SerializedName("portfolio") val portfolio: String,
    @field:SerializedName("primaryImage") val primaryImage: String,
    @field:SerializedName("primaryImageSmall") val primaryImageSmall: String,
    @field:SerializedName("region") val region: String,
    @field:SerializedName("reign") val reign: String,
    @field:SerializedName("repository") val repository: String,
    @field:SerializedName("rightsAndReproduction") val rightsAndReproduction: String,
    @field:SerializedName("river") val river: String,
    @field:SerializedName("state") val state: String,
    @field:SerializedName("tags") val tags: Any,
    @field:SerializedName("subregion") val subregion: String,
    @field:SerializedName("title") val title: String
)

data class Measurement(
    val elementDescription: Any,
    val elementMeasurements: ElementMeasurements,
    val elementName: String
)

data class ElementMeasurements(
    val Height: Double
)