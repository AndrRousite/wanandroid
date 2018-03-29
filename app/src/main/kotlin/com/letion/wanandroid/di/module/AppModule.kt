package com.letion.wanandroid.di.module

import android.app.Application
import com.letion.wanandroid.data.mapper.Mapper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * <p>com.letion.wanandroid.di.module.AppModule
 * @describe 简要描述
 *
 * @author wuqi
 * @date 2018/3/26 0026
 */
@Module
class AppModule(private val application: Application){
    @Singleton
    @Provides
    fun provideApplication() : Application = application

    @Singleton
    @Provides
    fun provideMapper() : Mapper = Mapper()
}
