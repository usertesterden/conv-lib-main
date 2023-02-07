package com.battlecreek.secret_lib.database

import android.content.Context
import androidx.room.Room
import com.battlecreek.secret_lib.models.JP_DB_NAME
import com.battlecreek.secret_lib.models.JokerPopularity

class JokerPopularityRepository(
    private val jokerPopularityDao: JokerPopularityDao
) {

    suspend fun addJP(jokerPopularity: JokerPopularity) {
        jokerPopularityDao.add(jokerPopularity = jokerPopularity)
    }

    suspend fun getJP(): JokerPopularity? {
        return jokerPopularityDao.get()
    }

    companion object {
        fun build(context: Context): JokerPopularityRepository {
            return Room.databaseBuilder(context, JokerPopularityDatabase::class.java, JP_DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
                .jokerPopularityDao()
                .let { dao ->
                    JokerPopularityRepository(dao)
                }
        }
    }
}