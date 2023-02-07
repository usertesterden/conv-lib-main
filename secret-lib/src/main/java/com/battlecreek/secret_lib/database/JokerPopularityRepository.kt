package com.battlecreek.secret_lib.database

import com.battlecreek.secret_lib.models.JokerPopularity

class JokerPopularityRepository(
    private val jokerPopularityDao: JokerPopularityDao
) {

    suspend fun add(jokerPopularity: JokerPopularity) {
        jokerPopularityDao.add(jokerPopularity = jokerPopularity)
    }

    suspend fun get(): JokerPopularity? {
        return jokerPopularityDao.get()
    }

}