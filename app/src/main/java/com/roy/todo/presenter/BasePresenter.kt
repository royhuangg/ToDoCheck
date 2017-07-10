package com.roy.todo.presenter

import android.os.Bundle
import com.grasea.grandroid.mvp.GrandroidPresenter
import com.roy.todo.activities.BaseActivity

/**
 * Created by Roy on 2017/7/10.
 */

abstract class BasePresenter<C : BaseActivity<*>> : GrandroidPresenter<C>() {

    abstract fun onActivityCreate(bundle: Bundle?)

    open fun onActivityResume() {}

    open fun onActivityStop() {}

    open fun onActivityDestroy() {}

}