package com.alexberghii.home.feed.di

import com.alexberghii.core.di.CoreComponent
import com.alexberghii.core.di.scopes.FeatureScope
import com.alexberghii.home.feed.FeedFragment
import dagger.Component


@FeatureScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [FeedModule::class]
)
interface FeedComponent {

    fun inject(feedFragment: FeedFragment)

    @Component.Factory
    interface Factory {

        fun create(coreComponent: CoreComponent, feedModule: FeedModule): FeedComponent
    }
}