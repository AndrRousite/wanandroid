package com.letion.wanandroid.base

import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import com.letion.wanandroid.App
import com.letion.wanandroid.di.component.ActivityComponent
import com.letion.wanandroid.di.module.ActivityModule
import com.letion.wanandroid.operation.navigation.Navigator
import com.letion.wanandroid.util.S
import com.letion.wanandroid.util.extensions.unSafeLazy
import com.letion.wanandroid.weiget.MaterialDialog
import io.armcha.arch.BaseMVPActivity
import javax.inject.Inject

/**
 * <p>com.letion.wanandroid.base.BaseActivity
 * @describe 简要描述
 *
 * @author wuqi
 * @date 2018/3/26 0026
 */
abstract class BaseActivity<V : BaseContract.View,P : BaseContract.Presenter<V>> : BaseMVPActivity<V,P>(),Navigator.FragmentChangeListener{
    @Inject
    lateinit var navigator : Navigator
    @Inject
    lateinit var  inflater : LayoutInflater
    private var dialog : MaterialDialog? = null

    val activityComponent : ActivityComponent by unSafeLazy {
        App.instance.appComponent + ActivityModule(this)
    }

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        navigator.fragmentChangeListener = this
        super.onCreate(savedInstanceState)
    }

    @CallSuper
    override fun onDestroy() {
        dialog?.dismiss()
        super.onDestroy()
    }

    @CallSuper
    override fun onBackPressed() {
        if (navigator.hasBackStack())
            navigator.goBack()
        else
            super.onBackPressed()
    }

    protected abstract fun injectDependencies()

    inline protected fun <reified T : Fragment> goTo(keepState: Boolean = true,
                                                     withCustomAnimation: Boolean = false,
                                                     arg: Bundle = Bundle.EMPTY) {
        navigator.goTo<T>(keepState = keepState,
                withCustomAnimation = withCustomAnimation,
                arg = arg)
    }

    fun showDialog(title: String, message: String, buttonText: String = "Close") {
        dialog = MaterialDialog(this).apply {
            message(message)
                    .title(title)
                    .addPositiveButton(buttonText) {
                        hide()
                    }
                    .show()
        }
    }

    fun showErrorDialog(message: String?, buttonText: String = "Close") {
        showDialog(getString(S.error_title), message ?: "", buttonText)
    }
}