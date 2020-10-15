package com.alexberghii.core.network

import com.alexberghii.core.network.response.FeedResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface AppService {

    companion object {

        const val BASE_URL = "https://pikabu.ru/page/interview/mobile-app/test-api/"
    }

    @GET("feed.php")
    suspend fun getFeed(): Response<List<FeedResponse>>

    @GET("story.php")
    suspend fun getStory(@Query("id") storyId: Int): Response<FeedResponse>
}