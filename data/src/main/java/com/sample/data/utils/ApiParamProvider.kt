package com.sample.data.utils

import com.sample.data.BuildConfig
import java.math.BigInteger
import java.security.MessageDigest

/**
 * This method returns the currentTimestamp
 */
fun getTimeStamp(): Long = System.currentTimeMillis() / 1000


/**
 * This method returns hashKey
 */
fun getHashKey(): String {
    val input =
        getTimeStamp().toString() + BuildConfig.APP_PRIVATE_KEY + BuildConfig.APP_PUBLIC_KEY
    return input.md5()
}

/**
 * This method return md5 value
 */
fun String.md5(): String {
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(toByteArray())).toString(16).padStart(32, '0')
}
