package com.roy.todo.activities

import android.os.Bundle
import com.grasea.grandroid.mvp.GrandroidActivity
import com.grasea.grandroid.mvp.GrandroidPresenter
import com.roy.todo.presenter.BasePresenter

/**
 * Created by Roy on 2017/7/10.
 */

abstract class BaseActivity<P : GrandroidPresenter<*>> : GrandroidActivity<P>() {

    open fun getBasePresenter(): BasePresenter<*> {
        if (presenter is BasePresenter) {
            return presenter as BasePresenter<*>
        }
        throw IllegalArgumentException("presenter must extend base")
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getBasePresenter().onActivityCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        getBasePresenter().onActivityResume()
    }

    override fun onStop() {
        super.onStop()
        getBasePresenter().onActivityStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        getBasePresenter().onActivityDestroy()
    }
    open fun initViews() {
        setContentView(getContentResource())
    }

    open fun getContentResource(): Int {
        return 0
    }

}