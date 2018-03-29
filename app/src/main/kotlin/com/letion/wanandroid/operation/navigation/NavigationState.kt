package com.letion.wanandroid.operation.navigation

/**
 * <p>com.letion.wanandroid.operation.navigation.NavigationState
 * @describe 简要描述
 *
 * @author wuqi
 * @date 2018/3/26 0026
 */
data class NavigationState constructor(var activeTag: String? = null,
                                       var firstTag: String? = null,
                                       var isCustomAnimationUsed: Boolean = false) {
    fun clear() {
        activeTag = null
        firstTag = null
    }
}