package com.hmn.movies.RoomDatabase.Popular

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MoviesDao {
    @get:Query("SELECT * FROM moviesentity")
    val movies: List<MoviesEntity>



    @Insert
    fun insert(moview: MoviesEntity)

}