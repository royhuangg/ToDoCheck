package com.roy.todo.activities

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import butterknife.OnClick
import com.ac2000.expassistant.util.DataDelegate
import com.github.nitrico.lastadapter.BR
import com.github.nitrico.lastadapter.LastAdapter
import com.grasea.grandroid.mvp.UsingPresenter
import com.roy.todo.R
import com.roy.todo.model.TodoThings
import com.roy.todo.presenter.MainPresenter
import com.roy.todo.util.loge
import jp.wasabeef.blurry.Blurry
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.intentFor

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
        val ages = IntArray(3)
        ages[0] = 1
        ages[1] = 1
        ages[2] = 2
        for ((i, k) in ages.withIndex()) {
            println("[$i]: $k")
        }
        ages.forEachIndexed { index, i -> println("[$index]: $i") }
//        viewBlur.post {
//            Blurry.with(this).radius(25).sampling(4).animate().onto(viewBlur)
//        }

        recyclerview.onFlingListener = object : RecyclerView.OnFlingListener() {
            override fun onFling(velocityX: Int, velocityY: Int): Boolean {
                Blurry.with(this@MainActivity).radius(25).animate().onto(viewBlur)
                return false
            }

        }

    }

    override fun getContentResource(): Int {
        return R.layout.activity_main
    }

    @OnClick(R.id.btnAdd)
    fun onClickAdd(view: View) {
        getPresenter().onAddNewThings(TodoThings("test1", false))
    }

    @OnClick(R.id.btnCalendar)
    fun onClickGoToCalendar(view: View) {
        startActivity(intentFor<CalendarActivity>())
    }

    fun onAddSuccess(todoThingsList: ArrayList<TodoThings>) {
        loge("size =${todoThingsList.size}")
    }
}

