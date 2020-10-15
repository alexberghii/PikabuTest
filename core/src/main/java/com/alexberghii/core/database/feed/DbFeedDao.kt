package com.alexberghii.core.database.feed

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*


@Dao
interface DbFeedDao {

    @Query("SELECT * FROM feed_items")
    fun getAllFeedItemsDataSourceFactory(): DataSource.Factory<Int, DbFeedItem>

    @Query("SELECT id FROM feed_items")
    fun getAllFeedItemsIdsLiveData(): LiveData<List<Int>>

    @Query("SELECT * FROM feed_items WHERE id = :id")
    suspend fun getFeedItemById(id: Int): DbFeedItem?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFeedItem(feedItem: DbFeedItem)

    @Delete
    suspend fun deleteFeedItem(feedItem: DbFeedItem)

    @Query("SELECT EXISTS(SELECT * FROM feed_items WHERE id = :id)")
    suspend fun hasItem(id: Int): Boolean
}