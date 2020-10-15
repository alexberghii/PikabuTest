package com.alexberghii.core.domain.model

import com.alexberghii.core.domain.extensions.mapToDomain
import com.alexberghii.core.mapper.Mapper
import com.alexberghii.core.network.response.FeedResponse


object FeedItemListMapper : Mapper<List<FeedResponse>, List<FeedItem>> {

    override fun map(input: List<FeedResponse>) = input.map { it.mapToDomain() }
}