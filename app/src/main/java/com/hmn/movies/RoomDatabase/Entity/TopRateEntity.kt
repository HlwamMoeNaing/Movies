package com.hmn.movies.RoomDatabase.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class TopRateEntity {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @ColumnInfo(name = "tittle")
    var tittle: String? = null

    @ColumnInfo(name = "release_date")
    var release_date: String? = null

    @ColumnInfo(name = "poster_path")
    var poster_path: String? = null
}