package com.letion.wanandroid

import android.app.Application
import com.letion.wanandroid.di.component.AppComponent
import com.letion.wanandroid.di.component.DaggerAppComponent
import com.letion.wanandroid.di.module.AppModule
import com.letion.wanandroid.util.extensions.unSafeLazy
import com.luseen.logger.LogType
import com.luseen.logger.Logger
import com.squareup.leakcanary.LeakCanary

/**
 * <p>com.letion.wanandroid.App
 * @describe 简要描述
 *
 * @author wuqi
 * @date 2018/3/26 0026
 */
class App : Application() {
    val appComponent: AppComponent by unSafeLazy {
        DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }

    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        if (LeakCanary.isInAnalyzerProcess(this)) return
        LeakCanary.install(this)
        instance = this
        Logger.Builder().isLoggable(BuildConfig.DEBUG)
                .logType(LogType.DEBUG)
                .tag("Letion")
                .setIsKotlin(true)
                .build()
    }
}