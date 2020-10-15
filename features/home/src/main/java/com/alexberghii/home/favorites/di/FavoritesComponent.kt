package com.alexberghii.home.favorites.di

import com.alexberghii.core.di.CoreComponent
import com.alexberghii.core.di.scopes.FeatureScope
import com.alexberghii.home.favorites.FavoritesFragment
import dagger.Component


@FeatureScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [FavoritesModule::class]
)
interface FavoritesComponent {

    fun inject(favoritesFragment: FavoritesFragment)

    @Component.Factory
    interface Factory {

        fun create(coreComponent: CoreComponent, favoritesModule: FavoritesModule): FavoritesComponent
    }
}