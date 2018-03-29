package com.letion.wanandroid.base

import io.armcha.arch.BaseMVPPresenter

/**
 * <p>com.letion.wanandroid.base.BasePresenter
 * @describe 简要描述
 *
 * @author wuqi
 * @date 2018/3/26 0026
 */
abstract class BasePresenter<V : BaseContract.View> : BaseMVPPresenter<V>(), BaseContract.Presenter<V>