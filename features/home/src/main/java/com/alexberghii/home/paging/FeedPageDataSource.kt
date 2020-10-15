package com.alexberghii.home.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.alexberghii.core.domain.model.FeedItem
import com.alexberghii.core.domain.model.FeedItemListMapper
import com.alexberghii.core.network.NetworkState
import com.alexberghii.core.network.repository.FeedRepository
import com.alexberghii.core.network.response.Result
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


const val INITIAL_PAGE = 1

class FeedPageDataSource(
    private val repository: FeedRepository,
    private val scope: CoroutineScope
) : PageKeyedDataSource<Int, FeedItem>() {

    val networkState = MutableLiveData<NetworkState>()

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, FeedItem>
    ) {
        networkState.postValue(NetworkState.Loading)
        scope.launch(CoroutineExceptionHandler { _, _ ->
            networkState.postValue(NetworkState.Error)
        }) {
            val result = repository.getFeed()
            if (result is Result.Success) {
                val data = FeedItemListMapper.map(result.value)
                callback.onResult(data, null, INITIAL_PAGE + 1)
                networkState.postValue(NetworkState.Success)
            } else {
                networkState.postValue(NetworkState.Error)
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, FeedItem>) {
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, FeedItem>) {
    }
}