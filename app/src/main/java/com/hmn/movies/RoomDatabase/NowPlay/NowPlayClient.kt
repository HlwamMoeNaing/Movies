package com.hmn.movies.RoomDatabase.NowPlay

import android.content.Context
import androidx.room.Room

class NowPlayClient private constructor(ctx:Context){
    val nowplayDatabase:NowPlayDatabase =
        Room.databaseBuilder(ctx,NowPlayDatabase::class.java,"MyNowPlay").build()
    companion object{
        private var nInstance:NowPlayClient? = null
        @Synchronized
        fun getNInstance(ctx:Context):NowPlayClient{
            if(nInstance==null){
                nInstance = NowPlayClient(ctx)

            }
            return nInstance!!
        }
    }
}