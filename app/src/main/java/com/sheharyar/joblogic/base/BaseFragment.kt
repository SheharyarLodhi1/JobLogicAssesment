package com.sheharyar.joblogic.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.sheharyar.joblogic.utils.Logger
import com.sheharyar.joblogic.utils.Tools

abstract class BaseFragment<F : ViewBinding>(val bindingFactory: (LayoutInflater) -> F) : Fragment() {

    lateinit var mFragmentViewBinding: F


    private val mShouldSave = true
    private var mIsInLeft: Boolean = false
    private var mIsOutLeft: Boolean = false
    protected var mIsCurrentScreen: Boolean = false
    private var mIsPush: Boolean = false

    private var mInitialized = true
    private var mViewCreated = false
    private var mViewDestroyed = false


    @LayoutRes
    protected abstract fun getLayoutId(): Int

    protected abstract fun isBindedToActivityLifeCycle(): Boolean

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mFragmentViewBinding = bindingFactory(layoutInflater)
        return mFragmentViewBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //initialzeViewModel()
        onVisible(view, savedInstanceState)
        mViewCreated = true
        mViewDestroyed = false
    }

    /*fun initialzeViewModel() {

        if (isBindedToActivityLifeCycle()) {
            mViewModel = ViewModelProviders.of(activity as FragmentActivity, getFactory())[getViewModelClass()]
        } else {
            mViewModel = ViewModelProviders.of(this@BaseFragment, getFactory())[getViewModelClass()]
        }


    }*/

    override fun onDestroyView() {
        mViewDestroyed = true
        mViewCreated = false
        onInVisible()
        //mFragmentViewDataBinding.unbind()
        super.onDestroyView()
    }


    fun isInitialized(): Boolean {
        return mInitialized
    }

    fun initialized() {
        mInitialized = false
    }

    fun isViewCreated(): Boolean {
        return mViewCreated
    }

    fun setInLeft(inLeft: Boolean) {
        mIsInLeft = inLeft
    }

    fun setOutLeft(outLeft: Boolean) {
        mIsOutLeft = outLeft
    }

    fun setCurrentScreen(currentScreen: Boolean) {
        mIsCurrentScreen = currentScreen
    }

    fun setPush(push: Boolean) {
        mIsPush = push
    }

    fun isShouldSave(): Boolean {
        return mShouldSave
    }

    protected abstract fun onVisible(view: View, savedInstanceState: Bundle?)

    protected fun handleAfterVisible() {}

    protected abstract fun onInVisible()

    fun startNewActivity(activity: Activity, clazz: Class<*>) {
        try {
            if (Tools.isActivityActive(activity)) {
//                RootValues.getInstance().TIME_STAMP_FOR_INTENT_INJECTION = System.currentTimeMillis().toString()
                val intent = Intent(activity, clazz)
//                intent.putExtra(KEY_INJECTION_EXTRA_STRING,
//                    BASE_EXTRA_TEXT_FOR_INTENT_INJECTION + RootValues.getInstance().TIME_STAMP_FOR_INTENT_INJECTION)
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


}