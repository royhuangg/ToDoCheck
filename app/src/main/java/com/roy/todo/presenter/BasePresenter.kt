package com.roy.todo.presenter

import android.os.Bundle
import com.grasea.grandroid.mvp.GrandroidPresenter
import com.grasea.grandroid.mvp.model.ModelProxy
import com.roy.todo.activities.BaseActivity
import com.roy.todo.data.TodoThingsModel
import com.roy.todo.model.TodoThings

/**
 * Created by Roy on 2017/7/10.
 */

abstract class BasePresenter<C : BaseActivity<*>> : GrandroidPresenter<C>() {

    val todoThingsModel: TodoThingsModel by lazy { ModelProxy.reflect(TodoThingsModel::class.java) }

    abstract fun onActivityCreate(bundle: Bundle?)

    open fun onActivityResume() {}

    open fun onActivityStop() {}

    open fun onActivityDestroy() {}


}