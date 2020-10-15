package com.alexberghii.home.feed

import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import com.alexberghii.core.database.feed.DbFeedRepository
import com.alexberghii.core.domain.extensions.mapToDb
import com.alexberghii.core.domain.model.FeedItem
import com.alexberghii.home.paging.FeedPageDataSourceFactory
import kotlinx.coroutines.launch


class FeedViewModel(
    private val dataSourceFactory: FeedPageDataSourceFactory,
    private val dbFeedRepository: DbFeedRepository
) : ViewModel() {

    private val pageSize = 50

    val networkState = Transformations.switchMap(dataSourceFactory.sourceLiveData) {
        it.networkState
    }

    val data = LivePagedListBuilder(dataSourceFactory, pageSize).build()
    val savedFeedItemsIds = dbFeedRepository.getAllFeedItemsIdsLiveData()

    fun saveOrDeleteFeedItem(feedItem: FeedItem) {
        viewModelScope.launch {
            val item = feedItem.mapToDb()
            if (dbFeedRepository.hasItem(item.id)) {
                dbFeedRepository.deleteFeedItem(item)
            } else {
                dbFeedRepository.insertFeedItem(item)
            }
        }
    }

    fun refresh() {
        dataSourceFactory.refresh()
    }
}