package com.alexberghii.core.network.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FeedResponse(
    @Json(name = "body")
    val body: String = "",
    @Json(name = "id")
    val id: Int = 0,
    @Json(name = "images")
    val images: List<String> = listOf(),
    @Json(name = "title")
    val title: String = ""
)