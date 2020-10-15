package com.alexberghii.core.domain.extensions

import com.alexberghii.core.database.feed.DbFeedItem
import com.alexberghii.core.domain.model.*
import com.alexberghii.core.network.response.FeedResponse


fun DbFeedItem.mapToDomain() = DbFeedItemToDomainMapper.map(this)

fun FeedItem.mapToDb() = DomainFeedItemToDbMapper.map(this)

fun FeedResponse.mapToDomain() = NetworkFeedItemToDomain.map(this)
