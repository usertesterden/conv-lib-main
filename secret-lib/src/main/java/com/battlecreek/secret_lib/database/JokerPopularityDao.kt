package com.battlecreek.secret_lib.database

import androidx.room.*
import com.battlecreek.secret_lib.models.JP_COL_CITY_ID
import com.battlecreek.secret_lib.models.JP_DB_NAME
import com.battlecreek.secret_lib.models.JokerPopularity

@Dao
interface JokerPopularityDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun add(jokerPopularity: JokerPopularity)

    @Query("SELECT * FROM $JP_DB_NAME WHERE $JP_COL_CITY_ID IS 1")
    suspend fun get(): JokerPopularity?

    @Query("SELECT * FROM $JP_DB_NAME")
    suspend fun getAll(): List<JokerPopularity>

    @Delete
    suspend fun delete(jokerPopularity: JokerPopularity)

    @Update
    suspend fun update(jokerPopularity: JokerPopularity)

}