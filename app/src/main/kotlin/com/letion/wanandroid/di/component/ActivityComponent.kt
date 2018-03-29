package com.letion.wanandroid.di.component

import com.letion.wanandroid.di.module.ActivityModule
import com.letion.wanandroid.di.scope.ActivityScope
import com.letion.wanandroid.operation.splash.SplashActivity
import dagger.Subcomponent

/**
 * <p>com.letion.wanandroid.di.component.ActivityComponent
 * @describe 简要描述
 *
 * @author wuqi
 * @date 2018/3/26 0026
 */
@ActivityScope
@Subcomponent(modules = [(ActivityModule::class)])
interface ActivityComponent {
    fun inject(splashActivity: SplashActivity)
}