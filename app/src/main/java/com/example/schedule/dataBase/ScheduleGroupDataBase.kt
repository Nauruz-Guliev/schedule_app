package com.example.schedule.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ScheduleGroups::class], version = 1, exportSchema = false)
abstract class ScheduleGroupDataBase :RoomDatabase(){
    abstract fun scheduleDao() :GroupsDAO

    companion object {
        @Volatile
        private var INSTANCE:ScheduleGroupDataBase?=null

        fun getDatabase(context: Context) : ScheduleGroupDataBase {
            val tempInstance = INSTANCE
            if(tempInstance!=null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ScheduleGroupDataBase::class.java,
                    "groups_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}