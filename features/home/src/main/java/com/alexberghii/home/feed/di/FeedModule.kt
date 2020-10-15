package com.alexberghii.home.feed.di

import androidx.lifecycle.viewModelScope
import com.alexberghii.commons.extensions.viewModel
import com.alexberghii.core.database.feed.DbFeedRepository
import com.alexberghii.core.di.scopes.FeatureScope
import com.alexberghii.core.network.repository.FeedRepository
import com.alexberghii.home.adapter.feed.FeedAdapter
import com.alexberghii.home.feed.FeedFragment
import com.alexberghii.home.feed.FeedViewModel
import com.alexberghii.home.paging.FeedPageDataSource
import com.alexberghii.home.paging.FeedPageDataSourceFactory
import dagger.Module
import dagger.Provides


@Module
class FeedModule(private val feedFragment: FeedFragment) {

    @FeatureScope
    @Provides
    fun provideFeedViewModel(dataSourceFactory: FeedPageDataSourceFactory, dbFeedRepository: DbFeedRepository) = feedFragment.viewModel {
        FeedViewModel(dataSourceFactory, dbFeedRepository)
    }

    @Provides
    fun provideFeedPageDataSource(viewModel: FeedViewModel, repository: FeedRepository) =
        FeedPageDataSource(repository, viewModel.viewModelScope)

    @FeatureScope
    @Provides
    fun provideFeedAdapter(feedViewModel: FeedViewModel) = FeedAdapter(feedViewModel, feedFragment::onFeedItemClicked)
}