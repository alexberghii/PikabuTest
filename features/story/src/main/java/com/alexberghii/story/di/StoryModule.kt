package com.alexberghii.story.di

import com.alexberghii.commons.extensions.viewModel
import com.alexberghii.core.database.feed.DbFeedRepository
import com.alexberghii.core.di.scopes.FeatureScope
import com.alexberghii.core.network.repository.FeedRepository
import com.alexberghii.story.StoryFragment
import com.alexberghii.story.StoryFragmentArgs
import com.alexberghii.story.StoryViewModel
import dagger.Module
import dagger.Provides


@Module
class StoryModule(private val storyFragment: StoryFragment) {

    @FeatureScope
    @Provides
    fun provideStoryViewModel(
        feedRepository: FeedRepository,
        dbFeedRepository: DbFeedRepository
    ) = storyFragment.viewModel {
        StoryViewModel(StoryFragmentArgs.fromBundle(storyFragment.requireArguments()).feedItemId, feedRepository, dbFeedRepository)
    }
}