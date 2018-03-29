package com.letion.wanandroid.util.imageloader

import android.app.ActivityManager
import android.content.Context
import android.support.v4.app.ActivityManagerCompat
import android.support.v4.view.accessibility.AccessibilityManagerCompat
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.cache.LruResourceCache
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions

/**
 * <p>com.letion.wanandroid.util.imageloader.LetionGlideModule
 * @describe 简要描述
 *
 * @author wuqi
 * @date 2018/3/26 0026
 */
@GlideModule
class LetionGlideModule : AppGlideModule() {
    override fun applyOptions(context: Context?, builder: GlideBuilder?) {
        super.applyOptions(context, builder)
        val activityManager = context?.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val memoryCacheSizeBytes = 1024 * 1024 * 20 // 20MB
        builder?.setMemoryCache(LruResourceCache(memoryCacheSizeBytes))
        builder?.setDefaultRequestOptions(RequestOptions().format(
                if (ActivityManagerCompat.isLowRamDevice(activityManager))
                    DecodeFormat.PREFER_RGB_565
                else
                    DecodeFormat.PREFER_ARGB_8888
        ))
    }

    override fun isManifestParsingEnabled(): Boolean {
        return !super.isManifestParsingEnabled()
    }
}