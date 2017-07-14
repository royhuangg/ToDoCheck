package com.roy.todo.model

import com.google.gson.annotations.SerializedName
import com.grasea.grandroid.database.Identifiable
import java.text.SimpleDateFormat

/**
 * Created by Roy on 2017/7/10.
 */

data class TodoThings(var eventName: String, var isDone: Boolean = false, @JvmField val _id: Int = 0,
                      var toDoDate: Long = System.currentTimeMillis(), var createDate: Long = System.currentTimeMillis()) : Identifiable {
    constructor() : this("")

    override fun get_id(): Int {
        return _id
    }

    override fun set_id(_id: Int?) {
    }


    fun getTodoDateFormatted(): String {
        return SimpleDateFormat("yyyy/MM/dd").format(toDoDate)
    }
}