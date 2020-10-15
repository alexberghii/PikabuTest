package com.alexberghii.story.di

import com.alexberghii.core.di.CoreComponent
import com.alexberghii.core.di.scopes.FeatureScope
import com.alexberghii.story.StoryFragment
import dagger.Component


@FeatureScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [StoryModule::class]
)
interface StoryComponent {

    fun inject(storyFragment: StoryFragment)

    @Component.Factory
    interface Factory {

        fun create(coreComponent: CoreComponent, storyModule: StoryModule): StoryComponent
    }
}