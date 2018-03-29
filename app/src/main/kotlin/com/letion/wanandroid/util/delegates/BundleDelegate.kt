package com.letion.wanandroid.util.delegates

import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.Fragment
import com.letion.wanandroid.util.extensions.isNull
import java.io.Serializable
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * <p>com.letion.wanandroid.util.delegates.BundleDelegate
 * @describe 简要描述
 *
 * @author wuqi
 * @date 2018/3/26 0026
 */


inline fun <reified VALUE> bundle() = object : ReadOnlyProperty<Fragment, VALUE> {

    private var value: VALUE? = null

    override fun getValue(thisRef: Fragment, property: KProperty<*>): VALUE {
        if (value.isNull) {
            value = thisRef.arguments[property.name] as VALUE
        }
        return value!!
    }
}

inline operator fun <reified V> Bundle.setValue(thisRef: Any?, property: KProperty<*>, value: V): Bundle {
    put(property.name to thisRef)
    return this
}

inline fun <reified V> Bundle.put(pair: Pair<String, V>): Bundle {
    val value = pair.second
    val key = pair.first
    when (value) {
        is Int -> putInt(key, value)
        is Long -> putLong(key, value)
        is String -> putString(key, value)
        is Parcelable -> putParcelable(key, value)
        is Serializable -> putSerializable(key, value)
        else -> throw UnsupportedOperationException("$value type not supported yet!!!")
    }
    return this
}

infix fun String.bundleWith(value: Any?): Bundle {
    val bundle = Bundle()
    val key = this
    bundle.put(key to value)
    return bundle
}