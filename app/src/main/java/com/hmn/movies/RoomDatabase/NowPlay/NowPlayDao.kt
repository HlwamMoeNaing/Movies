package com.hmn.movies.RoomDatabase.NowPlay

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NowPlayDao {
    @get:Query("SELECT * FROM nowplayentity")
    val nowplay:List<NowPlayEntity>

    @Insert
    fun insertNplay(nowplay:NowPlayEntity)
}