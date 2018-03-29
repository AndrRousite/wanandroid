package com.letion.wanandroid.operation.splash

import android.support.v4.app.Fragment
import com.bumptech.glide.request.FutureTarget
import com.bumptech.glide.request.target.Target
import com.letion.wanandroid.base.BaseActivity
import com.letion.wanandroid.util.imageloader.GlideApp
import com.luseen.logger.Logger
import java.io.File
import javax.inject.Inject

/**
 * <p>com.letion.wanandroid.operation.splash.SplashActivity
 * @describe 简要描述
 *
 * @author wuqi
 * @date 2018/3/26 0026
 */
class SplashActivity : BaseActivity<SplashContract.View, SplashContract.Presenter>(), SplashContract.View {

    @Inject
    protected lateinit var splashPresenter: SplashPresenter

    override fun toHome() {

    }

    override fun initPresenter(): SplashContract.Presenter = splashPresenter

    override fun injectDependencies() {
        activityComponent.inject(this)
        Thread(Runnable {
            val target: FutureTarget<File> = GlideApp.with(this).load("http://www.yidiandiantea.com/images/02.jpg").downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
            Logger.d(target.get().path)
        }).start()
    }

    override fun onChanged(currentTag: String, currentFragment: Fragment) {
    }

}