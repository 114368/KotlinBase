package com.example.kotlinbase.utils

import android.content.Context
import android.util.DisplayMetrics
import android.util.TypedValue

/**
 * 设备/尺寸工具类
 */
object DeviceUtil {

    @JvmStatic
    fun dp2px(context: Context, dp: Float): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp,
            context.resources.displayMetrics
        ).toInt()
    }

    @JvmStatic
    fun px2dp(context: Context, px: Float): Int {
        val density = context.resources.displayMetrics.density
        return (px / density + 0.5f).toInt()
    }

    @JvmStatic
    fun sp2px(context: Context, sp: Float): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP,
            sp,
            context.resources.displayMetrics
        ).toInt()
    }

    @JvmStatic
    fun px2sp(context: Context, px: Float): Int {
        val scaledDensity = context.resources.displayMetrics.scaledDensity
        return (px / scaledDensity + 0.5f).toInt()
    }

    @JvmStatic
    fun getScreenWidth(context: Context): Int {
        val dm: DisplayMetrics = context.resources.displayMetrics
        return dm.widthPixels
    }

    @JvmStatic
    fun getScreenHeight(context: Context): Int {
        val dm: DisplayMetrics = context.resources.displayMetrics
        return dm.heightPixels
    }
}
