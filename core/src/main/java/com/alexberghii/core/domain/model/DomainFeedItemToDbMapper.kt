package com.alexberghii.core.domain.model

import com.alexberghii.core.database.feed.DbFeedItem
import com.alexberghii.core.mapper.Mapper


object DomainFeedItemToDbMapper : Mapper<FeedItem, DbFeedItem> {

    override fun map(input: FeedItem) =
        DbFeedItem(
            id = input.id,
            title = input.title,
            body = input.body,
            images = input.images
        )
}