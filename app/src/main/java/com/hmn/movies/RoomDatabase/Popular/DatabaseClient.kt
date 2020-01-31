package com.hmn.movies.RoomDatabase.Popular

import android.content.Context
import androidx.room.Room

class DatabaseClient private constructor(ctx: Context) {
    val moviesDatabase: MoviesDatabase =
        Room.databaseBuilder(ctx, MoviesDatabase::class.java, "MyMovies").build()

    companion object {
        private var mInstance: DatabaseClient? = null

        @Synchronized
        fun getInstance(ctx: Context): DatabaseClient {
            if (mInstance == null) {
                mInstance =
                    DatabaseClient(ctx)

            }
            return mInstance!!
        }
    }
}



