package com.alexberghii.pikabutest.di

import com.alexberghii.core.di.CoreComponent
import com.alexberghii.core.di.scopes.AppScope
import com.alexberghii.pikabutest.PikabuApplication
import dagger.Component


@AppScope
@Component(dependencies = [CoreComponent::class])
interface AppComponent {

    fun inject(application: PikabuApplication)

    @Component.Factory
    interface Factory {
        fun create(coreComponent: CoreComponent): AppComponent
    }
}