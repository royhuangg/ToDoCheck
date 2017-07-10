package com.ac2000.expassistant.util

import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.grasea.grandroid.mvp.model.DefaultValue
import com.roy.todo.TodoApplication
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Created by Roy on 2017/7/7.
 */


object DataDelegate {
    fun <R, T> preferences(key: String, defaultValue: T) = SharePreferenceDelegate<R, T>(key, defaultValue)

}


class SharePreferenceDelegate<R, T>(val key: String, val defaultValue: T) : ReadWriteProperty<R, T> {
    fun getDefaultSharePreference(): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(TodoApplication.INSTANCE)
    }

    override fun getValue(thisRef: R, property: KProperty<*>): T {
        return getPreferencesValue(key, defaultValue)
    }

    private fun getPreferencesValue(key: String, defaultValue: T): T = with(getDefaultSharePreference()) {
        val res: Any = when (defaultValue) {
            is Int -> getInt(key, defaultValue)
            is String -> getString(key, defaultValue)
            is Long -> getLong(key, defaultValue)
            is Boolean -> getBoolean(key, defaultValue)
            is Float -> getFloat(key, defaultValue)
            else -> throw  IllegalArgumentException("value = $defaultValue not support this type.")
        }

        return res as T
    }

    override fun setValue(thisRef: R, property: KProperty<*>, value: T) {
        setPreferencesValue(key, defaultValue)
    }

    private fun setPreferencesValue(key: String, value: T) = with(getDefaultSharePreference()) {
        when (value) {
            is Int -> edit().putInt(key, value).apply()
            is String -> edit().putString(key, value).apply()
            is Long -> edit().putLong(key, value).apply()
            is Boolean -> edit().putBoolean(key, value).apply()
            is Float -> edit().putFloat(key, value).apply()
            else -> throw  IllegalArgumentException("value = $defaultValue not support this type.")
        }
    }


}