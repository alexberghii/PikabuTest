package com.alexberghii.core.domain.model

import com.alexberghii.core.mapper.Mapper
import com.alexberghii.core.network.response.FeedResponse


object NetworkFeedItemToDomain : Mapper<FeedResponse, FeedItem> {

    override fun map(input: FeedResponse) = FeedItem(
        id = input.id,
        title = input.title,
        body = input.body,
        images = input.images
    )
}