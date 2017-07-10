package com.roy.todo

import com.grasea.grandroid.app.GrandroidApplication

/**
 * Created by Roy on 2017/7/10.
 */

class TodoApplication : GrandroidApplication() {

    companion object {
        var INSTANCE: TodoApplication? = null

    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this

    }
}