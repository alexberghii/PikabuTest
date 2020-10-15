package com.alexberghii.story

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexberghii.core.database.feed.DbFeedRepository
import com.alexberghii.core.domain.extensions.mapToDb
import com.alexberghii.core.domain.extensions.mapToDomain
import com.alexberghii.core.domain.model.FeedItem
import com.alexberghii.core.network.NetworkState
import com.alexberghii.core.network.repository.FeedRepository
import com.alexberghii.core.network.response.Result
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch


class StoryViewModel(
    private val storyId: Int,
    private val feedRepository: FeedRepository,
    private val dbFeedRepository: DbFeedRepository
) : ViewModel() {

    val networkState: LiveData<NetworkState> = MutableLiveData()

    val data: LiveData<FeedItem> by lazy { MutableLiveData() }

    fun getStory() {
        viewModelScope.launch(CoroutineExceptionHandler { _, _ ->
            (networkState as MutableLiveData).postValue(NetworkState.Error)
        }) {
            val dbItem = dbFeedRepository.getFeedItemById(storyId)
            if (dbItem != null) {
                (data as MutableLiveData).postValue(dbItem.mapToDomain().apply { isSaved = true })
            } else {
                getStoryFromServer()
            }
        }
    }

    private suspend fun getStoryFromServer() {
        (networkState as MutableLiveData).postValue(NetworkState.Loading)
        val storyResponse = feedRepository.getStory(storyId)
        if (storyResponse is Result.Success) {
            (data as MutableLiveData).postValue(storyResponse.value.mapToDomain())
            (networkState).postValue(NetworkState.Success)
        } else {
            (networkState).postValue(NetworkState.Error)
        }
    }

    fun saveOrDeleteFeedItem() {
        val item = data.value
        if (item != null) {
            viewModelScope.launch {
                if (item.isSaved) {
                    dbFeedRepository.deleteFeedItem(item.mapToDb())
                    item.isSaved = false
                } else {
                    dbFeedRepository.insertFeedItem(item.mapToDb())
                    item.isSaved = true
                }
                (data as MutableLiveData).postValue(item)
            }
        }
    }

    fun refresh() {
        getStory()
    }
}