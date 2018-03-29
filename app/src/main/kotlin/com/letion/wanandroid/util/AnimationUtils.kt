package com.letion.wanandroid.util

import android.support.v4.view.animation.FastOutLinearInInterpolator
import android.support.v4.view.animation.FastOutSlowInInterpolator
import android.view.animation.Interpolator
import android.view.animation.LinearInterpolator

/**
 * <p>com.letion.wanandroid.util.AnimationUtils
 * @describe 简要描述
 *
 * @author wuqi
 * @date 2018/3/26 0026
 */
object AnimationUtils {
    val LINEAR_INTERPOLATOR: Interpolator = LinearInterpolator()
    val FAST_OUT_SLOW_IN_INTERPOLATOR: Interpolator = FastOutSlowInInterpolator()
    val FAST_OUT_LINEAR_IN_INTERPOLATOR: Interpolator = FastOutLinearInInterpolator()

    fun lerp(startValue: Int, endValue: Int, fraction: Float): Int {
        return startValue + Math.round(fraction * (endValue - startValue))
    }
}