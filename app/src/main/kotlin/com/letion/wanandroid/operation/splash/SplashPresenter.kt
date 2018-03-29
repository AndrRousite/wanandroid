package com.letion.wanandroid.operation.splash

import com.letion.wanandroid.base.BasePresenter
import com.letion.wanandroid.di.scope.ActivityScope
import com.letion.wanandroid.util.imageloader.GlideApp
import javax.inject.Inject

/**
 * <p>com.letion.wanandroid.operation.splash.SplashPresenter
 * @describe 简要描述
 *
 * @author wuqi
 * @date 2018/3/26 0026
 */
@ActivityScope
class SplashPresenter @Inject constructor() : BasePresenter<SplashContract.View>(),SplashContract.Presenter{
    override fun onPresenterCreate() {
        Thread(Runnable {
            Thread.sleep(3000)
            view?.toHome()
        }).start()
    }

}