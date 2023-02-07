package com.battlecreek.secret_lib.utils

import android.net.Uri
import java.util.TimeZone

class SecretKeeper {

    private val keeper = Uri.Builder()
    private var root: String = ""
    private var keys: List<String>? = null

    fun init(
        host: String,
        path: String,
        keys: List<String>,
    ) {
        keeper.scheme("https")
        keeper.authority(host)
        root = keeper.build().toString() + "/"
        keeper.path(path)
    }

    fun getRoot(): String {
        return root
    }

    fun getSecret(pursuitResult: String, twist: String): String {
        keeper.clearQuery()
        keeper.appendQueryParameter(keys?.get(0).toString(), keys?.get(1).toString())
        keeper.appendQueryParameter(keys?.get(2).toString(), pursuitResult)
        keeper.appendQueryParameter(keys?.get(3).toString(), pursuitResult)
        keeper.appendQueryParameter(keys?.get(4).toString(), twist)
        keeper.appendQueryParameter(keys?.get(5).toString(), TimeZone.getDefault().id)
        return keeper.build().toString()
    }

    fun provideAttention(pursuitResult: String): String {
        return when {
            pursuitResult.isEmpty() || pursuitResult == "null" -> {
                "organic"
            }
            else -> {
                pursuitResult
                    .replace("myapp://", "")
                    .substringBefore("/")
            }
        }
    }
}