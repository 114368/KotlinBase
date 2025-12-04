package com.example.kotlinbase.utils

import android.util.Log

/**
 * 日志工具类
 */
object LogUtil {

    @Volatile
    private var isDebug: Boolean = true
    private const val DEFAULT_TAG = "AppLog"

    @JvmStatic
    fun init(debug: Boolean) {
        isDebug = debug
    }

    @JvmStatic
    fun d(tag: String = DEFAULT_TAG, msg: String?) {
        if (isDebug) Log.d(tag, msg ?: "")
    }

    @JvmStatic
    fun i(tag: String = DEFAULT_TAG, msg: String?) {
        if (isDebug) Log.i(tag, msg ?: "")
    }

    @JvmStatic
    fun w(tag: String = DEFAULT_TAG, msg: String?, tr: Throwable? = null) {
        if (!isDebug) return
        if (tr != null) Log.w(tag, msg ?: "", tr) else Log.w(tag, msg ?: "")
    }

    @JvmStatic
    fun e(tag: String = DEFAULT_TAG, msg: String?, tr: Throwable? = null) {
        if (!isDebug) return
        if (tr != null) Log.e(tag, msg ?: "", tr) else Log.e(tag, msg ?: "")
    }
}
