package com.sheharyar.joblogic.utils

import android.util.Log

/**
 * @author Sheharyar Lodhi on 31-05-2020
 * iamsheharyarlodhi@gmai.com
 * sheharyar_09@outlook.com
 * +923024270232
 * Skype: sheharyar lodhi
 */
object Logger {

    public val TAG_CATCH_LOGS = "AirArabia_logs"

    /**
     * @param tag
     * @param message
     */
    public fun debugLog(tag: String, message: String) {
//        if (BuildConfig.LOG_ENABLED) {
        Log.d(tag, message)
//        }
    }

    /**
     * @param tag Tag
     * @param message Message
     * @param tr Exception
     */
    public fun debugLog(tag: String, message: String, tr: Throwable) {
//        if (BuildConfig.LOG_ENABLED) {
        Log.d(tag, message, tr)
//        }
    }

    /**
     * @param tag Tag
     * @param message Message
     * @param tr Exception
     */
    fun errorLog(tag: String, message: String, tr: Throwable) {
//        if (BuildConfig.LOG_ENABLED) {
        Log.e(tag, message, tr)
//        }
    }

    /**
     * @param tag
     * @param message
     */
    fun errorLog(tag: String, message: String) {
//        if (BuildConfig.LOG_ENABLED) {
        Log.e(tag, message)
//        }
    }

    /**
     * @param tag
     * @param message
     */
    fun warnLog(tag: String, message: String) {
//        if (BuildConfig.LOG_ENABLED) {
        Log.w(tag, message)
//        }
    }
}