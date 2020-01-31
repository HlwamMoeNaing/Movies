package com.hmn.movies.RoomDatabase.TopRate

import android.content.Context
import androidx.room.Room

class TopRateClient private constructor(ctx:Context){
  val toprateDatabase:TopRateDatabase=
      Room.databaseBuilder(ctx,TopRateDatabase::class.java,"MyTopRate").build()
    companion object{
        private var tInstance:TopRateClient ?= null
        @Synchronized
        fun getTInstance(ctx: Context):TopRateClient{
            if (tInstance == null){
                tInstance = TopRateClient(ctx)

            }
            return tInstance!!
        }
    }
}