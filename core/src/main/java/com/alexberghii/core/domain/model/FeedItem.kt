package com.alexberghii.core.domain.model


data class FeedItem(
    val id: Int,
    val title: String,
    val body: String,
    val images: List<String>,
) {

    var isSaved = false
}