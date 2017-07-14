package com.roy.todo.data

import com.grasea.grandroid.mvp.model.Query
import com.grasea.grandroid.mvp.model.Save
import com.roy.todo.model.TodoThings

/**
 * Created by Roy on 2017/7/14.
 */

interface TodoThingsModel {

    @Save(TodoThings::class)
    fun saveTodoThings(todoThings: TodoThings): Boolean

    @Query(TodoThings::class)
    fun getTodoThingsList(where: String = ""): ArrayList<TodoThings>

}