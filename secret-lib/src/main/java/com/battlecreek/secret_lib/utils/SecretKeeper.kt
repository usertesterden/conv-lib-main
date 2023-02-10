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
        isSecure: Boolean = true
    ) {
        this.keys = keys
        keeper.scheme(if (isSecure) "https" else "http")
        keeper.authority(host)
        root = keeper.build().toString() + "/"
        keeper.path(path)
    }

    fun getRoot(): String {
        return root
    }

    fun getSecret(pursuitResult: String, twist: String): String {
        keeper.clearQuery()
        keeper.appendQueryParameter(keys?.getOrNull(0).toString(), keys?.getOrNull(1).toString())
        keeper.appendQueryParameter(keys?.getOrNull(2).toString(), pursuitResult)
        keeper.appendQueryParameter(keys?.getOrNull(3).toString(), if (pursuitResult.nullOrEmpty()) "null" else "deeplink")
        keeper.appendQueryParameter(keys?.getOrNull(4).toString(), twist)
        keeper.appendQueryParameter(keys?.getOrNull(5).toString(), TimeZone.getDefault().id)
        return keeper.build().toString()
    }

    fun provideAttention(pursuitResult: String): String {
        return when {
            pursuitResult.nullOrEmpty() -> "organic"
            else -> pursuitResult
                .replace("myapp://", "")
                .substringBefore("/")
        }
    }

    fun contains(str: String?): Boolean {
        return str?.contains(getRoot()) == false
    }

    private fun String.nullOrEmpty(): Boolean {
        return isEmpty() || this == "null"
    }

    companion object {
        fun fixString(str: String): String {
            return str.replace("wv", "")
        }
    }
}