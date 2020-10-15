package com.alexberghii.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.alexberghii.core.database.converters.StringConverters
import com.alexberghii.core.database.feed.DbFeedDao
import com.alexberghii.core.database.feed.DbFeedItem


@Database(
    entities = [DbFeedItem::class],
    version = 1
)
@TypeConverters(StringConverters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun savedFeedDao(): DbFeedDao
}