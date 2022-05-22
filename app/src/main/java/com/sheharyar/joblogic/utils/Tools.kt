package com.sheharyar.joblogic.utils

import android.app.Activity

object Tools {
    /**
     * Function to check is activity is in active state and not finish yet
     */
    fun isActivityActive(activity: Activity?): Boolean {
        if (activity != null && !activity.isFinishing) {
            return true
        }
        return false
    }
}