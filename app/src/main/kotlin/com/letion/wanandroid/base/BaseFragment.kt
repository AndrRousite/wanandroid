package com.letion.wanandroid.base

import io.armcha.arch.BaseMVPFragment

/**
 * <p>com.letion.wanandroid.base.BaseFragment
 * @describe 简要描述
 *
 * @author wuqi
 * @date 2018/3/26 0026
 */
abstract class BaseFragment<V : BaseContract.View,P : BaseContract.Presenter<V>> : BaseMVPFragment<V,P>(){

}