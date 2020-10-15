package com.alexberghii.core.di.modules

import android.content.Context
import androidx.room.Room
import com.alexberghii.core.database.AppDatabase
import com.alexberghii.core.database.feed.DbFeedDao
import com.alexberghii.core.database.feed.DbFeedRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Context) =
        Room.databaseBuilder(context, AppDatabase::class.java, "AppDatabase").build()

    @Singleton
    @Provides
    fun provideFeedDao(database: AppDatabase) = database.savedFeedDao()

    @Singleton
    @Provides
    fun provideFeedRepository(dbFeedDao: DbFeedDao) = DbFeedRepository(dbFeedDao)
}