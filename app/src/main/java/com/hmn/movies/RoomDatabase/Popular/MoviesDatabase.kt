package com.hmn.movies.RoomDatabase.Popular

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [MoviesEntity::class],version = 1)
abstract class MoviesDatabase:RoomDatabase() {
abstract fun moviesDao(): MoviesDao
}


