package com.letion.wanandroid.util.extensions

import android.view.View

/**
 * <p>com.letion.wanandroid.util.extensions.ViewEx
 * @describe 简要描述
 *
 * @author wuqi
 * @date 2018/3/26 0026
 */
var View.scale: Float
    get() = Math.min(scaleX, scaleY)
    set(value) {
        scaleY = value
        scaleX = value
    }

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.onClick(function: () -> Unit) {
    setOnClickListener {
        function()
    }
}