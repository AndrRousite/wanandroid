package com.letion.wanandroid.operation.navigation

import android.support.v4.app.Fragment

/**
 * <p>com.letion.wanandroid.operation.navigation.NavigationEntity
 * @describe 简要描述
 *
 * @author wuqi
 * @date 2018/3/26 0026
 */
data class NavigationEntity(val fragment: Fragment, val backStrategy: BackStrategy)