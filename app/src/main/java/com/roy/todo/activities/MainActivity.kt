package com.roy.todo.activities

import android.support.v7.widget.LinearLayoutManager
import com.ac2000.expassistant.util.DataDelegate
import com.github.nitrico.lastadapter.BR
import com.github.nitrico.lastadapter.LastAdapter
import com.grasea.grandroid.mvp.UsingPresenter
import com.roy.todo.R
import com.roy.todo.model.TodoThings
import com.roy.todo.presenter.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*

@UsingPresenter(MainPresenter::class)
class MainActivity : BaseActivity<MainPresenter>() {

    var test: String by DataDelegate.preferences("aaa", "")

    override fun initViews() {
        super.initViews()
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.setHasFixedSize(true)

        var datas = ArrayList<TodoThings>()

        for (i in 0..10) {
            datas.add(TodoThings("test $i", i % 2 == 0))
        }
        LastAdapter(datas, BR.todo)
                .map<TodoThings>(R.layout.item_todo_thing)
                .into(recyclerview)
    }

    override fun getContentResource(): Int {
        return R.layout.activity_main
    }
}


