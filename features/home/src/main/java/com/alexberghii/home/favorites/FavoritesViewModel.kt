package com.alexberghii.home.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexberghii.core.database.feed.DbFeedRepository
import com.alexberghii.core.domain.extensions.mapToDb
import com.alexberghii.core.domain.model.DbFeedItemToDomainMapper
import com.alexberghii.core.domain.model.FeedItem
import kotlinx.coroutines.launch


class FavoritesViewModel(private val dbFeedRepository: DbFeedRepository) : ViewModel() {

    val data = dbFeedRepository.getAllFeedItemsLiveData(DbFeedItemToDomainMapper)

    fun deleteFeedItem(feedItem: FeedItem) {
        viewModelScope.launch {
            dbFeedRepository.deleteFeedItem(feedItem.mapToDb())
        }
    }
}