package com.roy.todo.presenter

import android.os.Bundle
import com.roy.todo.activities.MainActivity

/**
 * Created by Roy on 2017/7/10.
 */
class MainPresenter : BasePresenter<MainActivity>() {

    override fun onActivityCreate(bundle: Bundle?) {
        getContract().initViews()
    }
}