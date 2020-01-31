package com.hmn.movies.RoomDatabase.Upcoming

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [UpcomingEntity::class],version = 1)
abstract class UpcomingDatabase:RoomDatabase() {
    abstract fun upcomingDao():UpcomingDao
}