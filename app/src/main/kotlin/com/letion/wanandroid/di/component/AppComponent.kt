package com.letion.wanandroid.di.component

import com.letion.wanandroid.di.module.ActivityModule
import com.letion.wanandroid.di.module.ApiModule
import com.letion.wanandroid.di.module.AppModule
import dagger.Component
import javax.inject.Singleton

/**
 * <p>com.letion.wanandroid.di.component.AppComponent
 * @describe 简要描述
 *
 * @author wuqi
 * @date 2018/3/26 0026
 */
@Singleton
@Component(modules = [(AppModule::class), (ApiModule::class)])
interface AppComponent {
    operator fun plus(activityModule: ActivityModule): ActivityComponent
}