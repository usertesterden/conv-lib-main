package com.battlecreek.secret_lib.utils

import android.net.Uri
import java.util.TimeZone

class SecretKeeper(
    host: String,
    path: String,
    private val keys: List<String>,
) {

    private val keeper = Uri.Builder()
    private val root: String

    init {
        keeper.scheme("https")
        keeper.authority(host)
        root = keeper.build().toString() + "/"
        keeper.path(path)
    }

    fun getRoot(): String {
        return root
    }

    fun getSecret(deep: String, gadId: String): String {
        keeper.clearQuery()
        keeper.appendQueryParameter(keys[0], keys[1])
        keeper.appendQueryParameter(keys[2], deep)
        keeper.appendQueryParameter(keys[3], deep)
        keeper.appendQueryParameter(keys[4], gadId)
        keeper.appendQueryParameter(keys[5], TimeZone.getDefault().id)
        return keeper.build().toString()
    }
}