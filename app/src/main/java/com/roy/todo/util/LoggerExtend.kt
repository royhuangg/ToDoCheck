package com.roy.todo.util

import android.util.Log
import com.roy.todo.BuildConfig

/**
 * Created by Roy on 2017/7/14.
 */

val TAG = "Todo"

fun Any.logi(msg: String) {
    if (BuildConfig.DEBUG) {
        Log.i(TAG, msg)
    }
}

fun Any.logd(msg: String) {
    if (BuildConfig.DEBUG) {
        Log.d(TAG, msg)
    }
}


fun Any.loge(th: Throwable) {
    if (BuildConfig.DEBUG) {
        Log.e(TAG, null, th)
    }
}

fun Any.loge(msg: String) {
    if (BuildConfig.DEBUG) {
        Log.e(TAG, msg)
    }
}