package com.hmn.movies.RoomDatabase.Upcoming

import android.content.Context
import androidx.room.Room

class UpcomingClient private constructor(ctx:Context){
    val upcomingDatabase:UpcomingDatabase =
        Room.databaseBuilder(ctx,UpcomingDatabase::class.java,"MyUpcoming").build()
    companion object{

        var uInstance:UpcomingClient? = null
        @Synchronized
        fun getUpInstance(ctx: Context):UpcomingClient{
            if (uInstance == null){
                uInstance = UpcomingClient(ctx)
            }
            return uInstance!!

        }

    }
}