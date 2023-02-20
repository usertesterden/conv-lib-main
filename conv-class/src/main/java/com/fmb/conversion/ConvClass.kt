package com.fmb.conversion

import android.content.Context
import com.android.installreferrer.api.InstallReferrerClient
import com.android.installreferrer.api.InstallReferrerStateListener
import kotlinx.coroutines.suspendCancellableCoroutine
import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec
import kotlin.coroutines.resume

object ConvClass {

    internal var dataString:String? = null

    suspend fun getAttData(context: Context, keyMain: String) = suspendCancellableCoroutine {

        val referrerClient: InstallReferrerClient =
            InstallReferrerClient.newBuilder(context).build()
        referrerClient.startConnection(object : InstallReferrerStateListener {

            override fun onInstallReferrerSetupFinished(responseCode: Int) {
                when (responseCode) {
                    InstallReferrerClient.InstallReferrerResponse.OK -> {

                        if (referrerClient.installReferrer.installReferrer.contains("fb4a") || referrerClient.installReferrer.installReferrer.contains("facebook")){
                            it.resume(
                                decodeFacebookArray(
                                    encryptedString = getEncryptedData(referrerClient.installReferrer.installReferrer),
                                    nonce = getEncryptedNonce(referrerClient.installReferrer.installReferrer),
                                    key = keyMain
                                )
                            )
                        } else {
                            it.resume(null)
                        }


                    }
                    InstallReferrerClient.InstallReferrerResponse.FEATURE_NOT_SUPPORTED -> {
                        if (dataString == null){
                            it.resume(null)
                        } else {
                            it.resume(dataString)
                        }
                    }
                    InstallReferrerClient.InstallReferrerResponse.SERVICE_UNAVAILABLE -> {
                        if (dataString == null){
                            it.resume(null)
                        } else {
                            it.resume(dataString)
                        }

                    }
                }
            }

            override fun onInstallReferrerServiceDisconnected() {
                // Try to restart the connection on the next request to
                // Google Play by calling the startConnection() method.
            }
        })
    }


    private fun String.decodeHex(): ByteArray {
        check(length % 2 == 0) { "error" }

        return chunked(2)
            .map { it.toInt(16).toByte() }
            .toByteArray()
    }


    private fun getEncryptedData(reffererUrl: String): String {
        return reffererUrl.substringAfter("{\"data\":\"", reffererUrl)
            .substringBefore("\",\"nonce\"", reffererUrl)
    }

    private fun getEncryptedNonce(reffererUrl: String): String {
        return reffererUrl.substringAfter("\"nonce\":\"", reffererUrl)
            .substringBefore("\"}}", reffererUrl)
    }

    private fun decodeFacebookArray(encryptedString: String, key: String, nonce: String): String {
        val mKey = SecretKeySpec(key.decodeHex(), "AES/GCM/NoPadding")
        val mNonce = IvParameterSpec(nonce.decodeHex())
        val c = Cipher.getInstance("AES/GCM/NoPadding");
        c.init(Cipher.DECRYPT_MODE, mKey, mNonce)
        return String(c.doFinal(encryptedString.decodeHex()))
    }

    fun makeTest(jsonData: String){
        dataString = jsonData
    }
}