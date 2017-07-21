package com.roy.todo.activities

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import butterknife.OnClick
import com.github.nitrico.lastadapter.BR
import com.github.nitrico.lastadapter.LastAdapter
import com.grasea.grandroid.mvp.UsingPresenter
import com.roy.todo.R
import com.roy.todo.data.CalendarDate
import com.roy.todo.presenter.CalendarPresenter
import kotlinx.android.synthetic.main.activity_calendar.*
import java.text.SimpleDateFormat
import java.util.*

@UsingPresenter(CalendarPresenter::class)
class CalendarActivity : BaseActivity<CalendarPresenter>() {
    val today: Calendar = Calendar.getInstance()
    val currentCalendar: Calendar = Calendar.getInstance()

    override fun initViews() {
        super.initViews()
        recyclerview.layoutManager = GridLayoutManager(this, 7)
        recyclerview.setHasFixedSize(true)
        loadCalendar()
    }

    private fun loadCalendar() {
        tvTitle.text = getString(R.string.current_month, currentCalendar.get(Calendar.MONTH) + 1)
        LastAdapter(getCalendarData(), BR.calendarDate)
                .map<CalendarDate>(R.layout.item_calendar)
                .into(recyclerview)
    }


    private fun getCalendarData(): ArrayList<CalendarDate> {
        var datas = ArrayList<CalendarDate>()
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = currentCalendar.timeInMillis
        calendar.set(Calendar.DATE, 1)
//        val monthLastDate = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        val currentMonth = calendar.get(Calendar.MONTH)
        for (i in 0..41) {
            if (currentMonth != calendar.get(Calendar.MONTH)) {
                break
            }
            val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1
            if ((i % 7) == dayOfWeek) {
                datas.add(CalendarDate(calendar.get(Calendar.DATE).toString(), checkIsToday(calendar)))
                calendar.add(Calendar.DATE, 1)
            } else {
                datas.add(CalendarDate(""))
            }
        }
        return datas
    }

    private fun checkIsToday(currentCalendar: Calendar): Boolean {
        val format = SimpleDateFormat("yyyyMMdd")
        val todayStr = format.format(today.timeInMillis)
        val currentStr = format.format(currentCalendar.timeInMillis)
        return todayStr == currentStr
    }

    override fun getContentResource(): Int {
        return R.layout.activity_calendar
    }

    @OnClick(R.id.btnPrevious, R.id.btnNext)
    fun onClick(view: View) {
        when (view.id) {
            R.id.btnPrevious -> currentCalendar.add(Calendar.MONTH, -1)
            R.id.btnNext -> currentCalendar.add(Calendar.MONTH, 1)
        }
        loadCalendar()
    }
}

