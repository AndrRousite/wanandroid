package com.letion.wanandroid.operation.navigation

import com.letion.wanandroid.util.Experimental

/**
 * <p>com.letion.wanandroid.operation.navigation.BackStrategy
 * @describe 简要描述
 *
 * @author wuqi
 * @date 2018/3/26 0026
 */
@Experimental
sealed class BackStrategy {
    object KEEP : BackStrategy()
    object DESTROY : BackStrategy()
}