package com.hmn.movies.RoomDatabase.TopRate

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TopRateEntity::class],version = 1)
abstract class TopRateDatabase:RoomDatabase() {
    abstract fun topRateDao():TopRateDao

}