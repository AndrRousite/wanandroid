package com.letion.wanandroid.base

import io.armcha.arch.BaseMVPContract

/**
 * <p>com.letion.wanandroid.base.BaseContract
 * @describe 简要描述
 *
 * @author wuqi
 * @date 2018/3/26 0026
 */
interface BaseContract{
    interface View : BaseMVPContract.View

    interface Presenter<V : BaseMVPContract.View> : BaseMVPContract.Presenter<V>
}