package com.hmn.movies.RoomDatabase.Upcoming

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.hmn.movies.Model.Upcoming


@Dao
interface UpcomingDao {
    @get:Query("SELECT * FROM upcomingentity")
    val upcoming:List<UpcomingEntity>

    @Insert
    fun saveUpvoming(upcoming:UpcomingEntity)
}