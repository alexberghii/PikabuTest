package com.alexberghii.core.di

import android.content.Context
import com.alexberghii.core.database.feed.DbFeedRepository
import com.alexberghii.core.di.modules.DatabaseModule
import com.alexberghii.core.di.modules.NetworkModule
import com.alexberghii.core.network.repository.FeedRepository
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [DatabaseModule::class, NetworkModule::class])
interface CoreComponent {

    fun context(): Context

    fun feedRepository(): FeedRepository

    fun savedFeedRepository(): DbFeedRepository

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance appContext: Context): CoreComponent
    }
}