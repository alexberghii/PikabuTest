package com.alexberghii.core.domain.model

import com.alexberghii.core.database.feed.DbFeedItem
import com.alexberghii.core.mapper.Mapper


object DbFeedItemToDomainMapper : Mapper<DbFeedItem, FeedItem> {

    override fun map(input: DbFeedItem) = FeedItem(
        id = input.id,
        title = input.title,
        body = input.body,
        images = input.images
    )
}