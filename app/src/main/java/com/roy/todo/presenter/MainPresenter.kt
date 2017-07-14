package com.roy.todo.presenter

import android.os.Bundle
import com.roy.todo.activities.MainActivity
import com.roy.todo.model.TodoThings

/**
 * Created by Roy on 2017/7/10.
 */
class MainPresenter : BasePresenter<MainActivity>() {

    override fun onActivityCreate(bundle: Bundle?) {
        getContract().initViews()
    }

    fun onAddNewThings(todoThings: TodoThings) {
        todoThingsModel.saveTodoThings(todoThings)
        getContract().onAddSuccess(todoThingsModel.getTodoThingsList())
    }
}