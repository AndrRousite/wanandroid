package com.letion.wanandroid.di.module

import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import com.letion.wanandroid.di.scope.ActivityScope
import dagger.Module
import dagger.Provides

/**
 * <p>com.letion.wanandroid.di.module.ActivityModule
 * @describe 简要描述
 *
 * @author wuqi
 * @date 2018/3/26 0026
 */
@Module
class ActivityModule(private val activity : AppCompatActivity){
    @ActivityScope
    @Provides
    fun providesActivity() : AppCompatActivity = activity

    @ActivityScope
    @Provides
    fun providesLayoutInflater(activity: AppCompatActivity): LayoutInflater =
            LayoutInflater.from(activity)

    @ActivityScope
    @Provides
    fun providesFragmentManager(activity: AppCompatActivity): FragmentManager =
            activity.supportFragmentManager
}