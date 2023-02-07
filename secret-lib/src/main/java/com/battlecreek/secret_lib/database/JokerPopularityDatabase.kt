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
}