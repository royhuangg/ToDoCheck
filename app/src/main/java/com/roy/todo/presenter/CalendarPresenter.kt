package com.roy.todo.presenter

import android.os.Bundle
import com.roy.todo.activities.CalendarActivity
import com.roy.todo.activities.MainActivity
import com.roy.todo.model.TodoThings

/**
 * Created by Roy on 2017/7/10.
 */
class CalendarPresenter : BasePresenter<CalendarActivity>() {

    override fun onActivityCreate(bundle: Bundle?) {
        getContract().initViews()
    }

}