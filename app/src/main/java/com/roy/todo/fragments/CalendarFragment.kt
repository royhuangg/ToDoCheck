package com.roy.todo.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.nitrico.lastadapter.BR
import com.github.nitrico.lastadapter.LastAdapter
import com.roy.todo.R
import com.roy.todo.data.CalendarDate
import com.roy.todo.util.logd
import kotlinx.android.synthetic.main.fragment_calendar.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Roy on 2017/7/25.
 */

class CalendarFragment : Fragment() {
    val today: Calendar = Calendar.getInstance()
    lateinit var currentCalendar: Calendar

    companion object {
        @JvmStatic fun newInstance(calendar: Calendar): CalendarFragment {
            val fragment = CalendarFragment()
            val arg = Bundle()
            fragment.currentCalendar = calendar
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
//        val inflate = LayoutInflater.from(activity).inflate(R.layout.fragment_calendar, container, false)
        val inflate = inflater!!.inflate(R.layout.fragment_calendar, container, false)
        return inflate
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()

    }

    private fun initViews() {
        recyclerview.layoutManager = GridLayoutManager(activity, 7)
        recyclerview.setHasFixedSize(true)
        loadCalendar()
    }

    private fun loadCalendar() {
//        tvTitle.text = getString(R.string.current_month, currentCalendar.get(Calendar.MONTH) + 1)
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
}