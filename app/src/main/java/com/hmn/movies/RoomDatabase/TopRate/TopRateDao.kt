package com.hmn.movies.RoomDatabase.TopRate

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import org.w3c.dom.ls.LSException

@Dao
interface TopRateDao {
    @get:Query("SELECT * FROM toprateentity")
    val topRate:List<TopRateEntity>
    @Insert
    fun saveTopRate(toprate:TopRateEntity)
}