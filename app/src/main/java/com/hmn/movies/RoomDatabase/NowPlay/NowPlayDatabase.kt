package com.hmn.movies.RoomDatabase.NowPlay

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities =  [NowPlayEntity::class],version = 1)
abstract class NowPlayDatabase:RoomDatabase() {
    abstract fun nowplayDao():NowPlayDao

}