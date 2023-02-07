package com.battlecreek.secret_lib.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.battlecreek.secret_lib.models.JP_DB_NAME
import com.battlecreek.secret_lib.models.JokerPopularity


@Database(entities = [JokerPopularity::class], version = 1, exportSchema = false)
abstract class JokerPopularityDatabase : RoomDatabase() {

    abstract fun jokerPopularityDao(): JokerPopularityDao

    companion object {

        @Volatile
        private var INSTANCE: JokerPopularityDatabase? = null

        private fun buildDB(context: Context) =
            Room.databaseBuilder(context, JokerPopularityDatabase::class.java, JP_DB_NAME)
                .fallbackToDestructiveMigration()
                .build()

        fun getInstance(context: Context): JokerPopularityDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDB(context).also {
                    INSTANCE = it
                }
            }

    }
}