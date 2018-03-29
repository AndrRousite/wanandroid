package com.letion.wanandroid.operation.splash

import com.letion.wanandroid.base.BaseContract

/**
 * <p>com.letion.wanandroid.operation.splash.SplashContract
 * @describe 简要描述
 *
 * @author wuqi
 * @date 2018/3/26 0026
 */
interface SplashContract{
    interface View : BaseContract.View{
        fun toHome()
    }

    interface Presenter : BaseContract.Presenter<View>
}