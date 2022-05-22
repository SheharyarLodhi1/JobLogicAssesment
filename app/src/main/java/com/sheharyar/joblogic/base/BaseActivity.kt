package com.sheharyar.joblogic.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.sheharyar.joblogic.utils.Logger
import com.sheharyar.joblogic.utils.Tools

abstract class BaseActivity<T : ViewBinding>(val bindingFactory: (LayoutInflater) -> T) :
    AppCompatActivity() {
    private lateinit var viewBinding: T
    private var active = false

    /**
     * setup content layout
     * @return layout id
     */
    @LayoutRes
    protected abstract fun getLayoutId(): Int

    protected abstract fun init()
    private fun resumeScreen() {}
    private fun pauseScreen() {}
    private fun stopScreen() {}
    private fun destroyScreen() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        (super.onCreate(savedInstanceState))
        viewBinding = bindingFactory(layoutInflater)
        setContentView(viewBinding.root)
        init()
    }

    public override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

    }//onPostCreate ends

    override fun onStart() {
        super.onStart()
        active = true
    }//onStart ends


    override fun onPostResume() {
        super.onPostResume()
    }//onPostResume ends

    override fun onResume() {
        super.onResume()
        System.gc()
        System.runFinalization()
        resumeScreen()
    }//onResume ends

    override fun onPause() {
        super.onPause()
//        hideLoading()
        pauseScreen()
    }//onPause ends

    override fun onStop() {
        active = false
        super.onStop()
        stopScreen()
    }//onStop ends

    fun isActive(): Boolean {
        return active
    }//isActive ends

    override fun onDestroy() {
        super.onDestroy()
        System.gc()
        System.runFinalization()
        destroyScreen()
    }//onDestroy ends

    /**
     * Method to Hide Loading
     **//*
    override fun hideLoading() {
        if (this::dialog.isInitialized && dialog.isShowing) {
            dialog.dismiss()
        }
    }//hideLoading ends

    *//**
     * Method to Show Loading
     **//*
    override fun showLoading() {
        if (this::dialog.isInitialized && !dialog.isShowing) {
            dialog.show()
        }
    }//showLoading ends*/

    /**`
     * Start new activity with having previous activity
     *
     * @param activity
     * @param clazz
     */
    fun startNewActivity(activity: Activity, clazz: Class<*>) {
        try {
            if (Tools.isActivityActive(activity)) {
                val intent = Intent(activity, clazz)
                activity.startActivity(intent)
            }
        } catch (e: Exception) {
            Logger.debugLog(Logger.TAG_CATCH_LOGS, e.message!!)
        }

    }

    /**
     * Start new activity and close previous activity
     *
     * @param activity
     * @param clazz
     */
    fun startNewActivityAndClear(activity: Activity, clazz: Class<*>) {
        try {
            if (Tools.isActivityActive(activity)) {
//                RootValues.getInstance().TIME_STAMP_FOR_INTENT_INJECTION = System.currentTimeMillis().toString()
                val intent = Intent(activity, clazz)
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//                        or Intent.FLAG_ACTIVITY_CLEAR_TOP)
//                intent.putExtra(KEY_INJECTION_EXTRA_STRING,
//                    BASE_EXTRA_TEXT_FOR_INTENT_INJECTION + RootValues.getInstance().TIME_STAMP_FOR_INTENT_INJECTION)
                activity.startActivity(intent)
                activity.finish()
            }
        } catch (e: Exception) {
            Logger.debugLog(Logger.TAG_CATCH_LOGS, e.message!!)
        }

    }

    /**
     * Start new activity and close previous activity with extra data
     *
     * @param activity
     * @param clazz
     * @param bundle
     */
    fun startNewActivityAndClear(activity: Activity, clazz: Class<*>, bundle: Bundle) {
        try {
            if (Tools.isActivityActive(activity)) {
//                RootValues.getInstance().TIME_STAMP_FOR_INTENT_INJECTION = System.currentTimeMillis().toString()
                val intent = Intent(activity, clazz)
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//                        or Intent.FLAG_ACTIVITY_CLEAR_TOP)
//                intent.putExtra(KEY_INJECTION_EXTRA_STRING,
//                    BASE_EXTRA_TEXT_FOR_INTENT_INJECTION + RootValues.getInstance().TIME_STAMP_FOR_INTENT_INJECTION)
                intent.putExtras(bundle)
                activity.startActivity(intent)
                activity.finish()
            }
        } catch (e: Exception) {
            Logger.debugLog(Logger.TAG_CATCH_LOGS, e.message!!)
        }

    }

    /**
     * Start new Activity with adding bundle
     *
     * @param activity
     * @param clazz
     * @param bundle
     */
    fun startNewActivity(activity: Activity, clazz: Class<*>, bundle: Bundle) {
        try {
            if (Tools.isActivityActive(activity)) {
//                RootValues.getInstance().TIME_STAMP_FOR_INTENT_INJECTION = System.currentTimeMillis().toString()
                val intent = Intent(activity, clazz)
//                intent.putExtra(KEY_INJECTION_EXTRA_STRING,
//                    BASE_EXTRA_TEXT_FOR_INTENT_INJECTION + RootValues.getInstance().TIME_STAMP_FOR_INTENT_INJECTION)
                intent.putExtras(bundle)
                activity.startActivity(intent)
            }
        } catch (e: Exception) {
            Logger.debugLog(Logger.TAG_CATCH_LOGS, e.message!!)
        }

    }

    /**
     * Start new Activity for Result
     *
     * @param activity
     * @param clazz
     * @param requestCode
     * @param bundle
     */
    fun startNewActivityForResult(activity: Activity, clazz: Class<*>, requestCode: Int, bundle: Bundle) {

    }

    /**
     * Start new Activity for Result
     *
     * @param activity
     * @param clazz
     * @param requestCode
     */
    fun startNewActivityForResult(activity: Activity, clazz: Class<*>, requestCode: Int) {

    }

    open fun getStringsFromAssets(resID: Int): String? {
        return getString(resID)
    }

}