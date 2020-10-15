package com.alexberghii.core.database.feed

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.alexberghii.core.mapper.Mapper


class DbFeedRepository(private val dbFeedItemDao: DbFeedDao) {

    fun <T> getAllFeedItemsLiveData(mapper: Mapper<DbFeedItem, T>, pageSize: Int = 50): LiveData<PagedList<T>> {
        return dbFeedItemDao.getAllFeedItemsDataSourceFactory().map { mapper.map(it) }.toLiveData(pageSize = pageSize)
    }

    fun getAllFeedItemsIdsLiveData() = dbFeedItemDao.getAllFeedItemsIdsLiveData()

    suspend fun getFeedItemById(id: Int): DbFeedItem? = dbFeedItemDao.getFeedItemById(id)

    suspend fun insertFeedItem(feedItem: DbFeedItem) = dbFeedItemDao.insertFeedItem(feedItem)

    suspend fun deleteFeedItem(feedItem: DbFeedItem) = dbFeedItemDao.deleteFeedItem(feedItem)

    suspend fun hasItem(id: Int) = dbFeedItemDao.hasItem(id)
}