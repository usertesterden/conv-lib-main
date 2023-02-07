package com.battlecreek.secret_lib.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

const val JP_DB_NAME = "joker_popularity"
const val JP_COL_CITY_ID = "city_id"
const val JP_COL_POPULARITY_SECRET = "popularity_secret"
const val JP_COL_IS_ANGER = "is_anger"

@Entity(tableName = JP_DB_NAME)
data class JokerPopularity(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = JP_COL_CITY_ID)
    val cityId: Int = 1,

    @ColumnInfo(name = JP_COL_POPULARITY_SECRET)
    val popularitySecret: String,

    @ColumnInfo(name = JP_COL_IS_ANGER)
    val isAnger: Boolean,

)
