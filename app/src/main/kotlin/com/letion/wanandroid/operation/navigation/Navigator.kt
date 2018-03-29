package com.letion.wanandroid.operation.navigation

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import com.letion.wanandroid.di.scope.ActivityScope
import com.letion.wanandroid.util.ANIM
import com.letion.wanandroid.util.Experimental
import com.letion.wanandroid.util.ID
import javax.inject.Inject

/**
 * <p>com.letion.wanandroid.operation.navigation.Navigator
 * @describe 简要描述
 *
 * @author wuqi
 * @date 2018/3/26 0026
 */
@ActivityScope
class Navigator @Inject constructor(private val activity: AppCompatActivity,
                                    private val fragmentManager: FragmentManager) {
    private var fragmentMap: LinkedHashMap<String, NavigationEntity> = linkedMapOf()
    private val containerId = ID.container
    private var activeTag: String? = null
    private var rootTag: String? = null
    private var isCustomAnimationUsed = false
    lateinit var fragmentChangeListener: FragmentChangeListener

    fun getState(): NavigationState {
        return NavigationState(activeTag, rootTag, isCustomAnimationUsed)
    }

    fun restore(state: NavigationState) {
        activeTag = state.activeTag
        rootTag = state.firstTag
        isCustomAnimationUsed = state.isCustomAnimationUsed
        state.clear()

        fragmentMap.clear()
        fragmentManager.fragments
                .filter { it.tag.contains(activity.applicationContext.packageName) }
                .forEach {
                    fragmentMap.put(it.tag, NavigationEntity(it, BackStrategy.KEEP)) //FIXME
                }

        fragmentManager.inTransaction {
            fragmentMap
                    .filter { it.key != activeTag }
                    .forEach {
                        hide(it.value.fragment)
                    }
            show(fragmentMap[activeTag]?.fragment)
        }
        invokeFragmentChangeListener(activeTag)
    }

    inline fun <reified T : Fragment> goTo(keepState: Boolean = true,
                                           withCustomAnimation: Boolean = false,
                                           arg: Bundle = Bundle.EMPTY,
                                           @Experimental
                                           backStrategy: BackStrategy = BackStrategy.KEEP) {
        val tag = T::class.java.name
        goTo(tag, keepState, withCustomAnimation, arg, backStrategy)
    }

    @PublishedApi
    internal fun goTo(tag: String,
                      keepState: Boolean,
                      withCustomAnimation: Boolean,
                      arg: Bundle,
                      backStrategy: BackStrategy) {
        if (activeTag == tag)
            return

        if (!fragmentMap.containsKey(tag) || !keepState) {
            val fragment = Fragment.instantiate(activity, tag)
            if (!arg.isEmpty) {
                fragment.arguments = arg
            }


            if (!keepState) {
                val weakFragment = fragmentManager.findFragmentByTag(tag)
                weakFragment?.let {
                    fragmentManager.inTransaction {
                        remove(weakFragment)
                    }
                }
            }
            fragmentManager.inTransaction {
                setFragmentTransaction(this, withCustomAnimation)
                add(containerId, fragment, tag)
            }


            fragmentMap.put(tag, NavigationEntity(fragment, backStrategy))

            if (activeTag == null) {
                rootTag = tag
            }
        }

        fragmentManager.inTransaction {
            setFragmentTransaction(this, withCustomAnimation)
            fragmentMap
                    .filter { it.key != tag }
                    .forEach {
                        hide(it.value.fragment)
                    }
            show(fragmentMap[tag]?.fragment)
        }
        activeTag = tag
        invokeFragmentChangeListener(tag)

        fragmentMap.replaceValue(tag, fragmentMap[tag])

    }

    fun hasBackStack(): Boolean {
        return fragmentMap.size > 1 && activeTag != rootTag
    }

    fun goBack() {
        val screen = fragmentMap[activeTag]
        val backStrategy = screen?.backStrategy
        val isKeep = backStrategy is BackStrategy.KEEP
        fragmentManager.inTransaction {
            if (isCustomAnimationUsed)
                setCustomAnimations(0, ANIM.slide_out_finish)
            if (isKeep) {
                hide(screen?.fragment)
            } else if (backStrategy is BackStrategy.DESTROY) {
                remove(screen.fragment)
            }
        }

        val keys = fragmentMap.keys
        val currentTag = if (isKeep) {
            val index = keys.indexOf(activeTag)
            keys.elementAt(index - 1)
        } else {
            fragmentMap.remove(activeTag)
            keys.last()
        }

        fragmentManager.inTransaction {
            if (!isCustomAnimationUsed) {
                setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            }
            show(fragmentMap[currentTag]?.fragment)
        }
        isCustomAnimationUsed = false
        activeTag = currentTag
        invokeFragmentChangeListener(currentTag)
    }

    private fun setFragmentTransaction(transaction: FragmentTransaction, withCustomAnimation: Boolean) {
        isCustomAnimationUsed = withCustomAnimation
        if (withCustomAnimation)
            transaction.setCustomAnimations(ANIM.slide_in_start, 0)
        else
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
    }

    private fun invokeFragmentChangeListener(tag: String?) {
        tag?.let {
            val entity = fragmentMap[it]
            entity?.let {
                fragmentChangeListener.onChanged(tag,it.fragment)
            }
        }
    }

    private inline fun FragmentManager.inTransaction(transaction: FragmentTransaction.() -> Unit) {
        val fragmentTransaction = this.beginTransaction()
        fragmentTransaction.transaction()
        fragmentTransaction.commitNow()
    }

    private fun <K, V> MutableMap<K, V>.replaceValue(key: K, value: V?) {
        this.remove(key)
        value?.let {
            this.put(key, value)
        }
    }

    interface FragmentChangeListener {
        fun onChanged(currentTag: String, currentFragment: Fragment)
    }

}