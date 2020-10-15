package com.alexberghii.home.favorites.di

import com.alexberghii.commons.extensions.viewModel
import com.alexberghii.core.database.feed.DbFeedRepository
import com.alexberghii.core.di.scopes.FeatureScope
import com.alexberghii.home.adapter.feed.FavoritesAdapter
import com.alexberghii.home.favorites.FavoritesFragment
import com.alexberghii.home.favorites.FavoritesViewModel
import dagger.Module
import dagger.Provides


@Module
class FavoritesModule(private val favoritesFragment: FavoritesFragment) {

    @FeatureScope
    @Provides
    fun provideFavoritesViewModel(dbFeedRepository: DbFeedRepository) = favoritesFragment.viewModel {
        FavoritesViewModel(dbFeedRepository)
    }

    @FeatureScope
    @Provides
    fun provideFeedAdapter(favoritesViewModel: FavoritesViewModel) = FavoritesAdapter(favoritesViewModel, favoritesFragment::onFeedItemClicked)
}