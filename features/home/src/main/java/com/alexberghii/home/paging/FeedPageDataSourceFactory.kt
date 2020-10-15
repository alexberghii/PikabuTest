package com.alexberghii.home.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.alexberghii.core.domain.model.FeedItem
import javax.inject.Inject
import javax.inject.Provider


class FeedPageDataSourceFactory @Inject constructor(private val dataSourceProvider: Provider<FeedPageDataSource>) : DataSource.Factory<Int, FeedItem>() {

    var sourceLiveData = MutableLiveData<FeedPageDataSource>()

    override fun create(): DataSource<Int, FeedItem> {
        val dataSource = dataSourceProvider.get()
        sourceLiveData.postValue(dataSource)
        return dataSource
    }

    fun refresh() {
        sourceLiveData.value?.invalidate()
    }
}