package com.alexberghii.core.network.repository

import com.alexberghii.core.network.AppService
import com.alexberghii.core.network.extension.getResult


class FeedRepository(private val appService: AppService) {

    suspend fun getFeed() = appService.getFeed().getResult()

    suspend fun getStory(storyId: Int) = appService.getStory(storyId).getResult()
}