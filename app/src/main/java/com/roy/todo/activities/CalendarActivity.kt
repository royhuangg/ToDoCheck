package com.roy.todo.activities

import android.view.View
import butterknife.OnClick
import com.grasea.grandroid.mvp.UsingPresenter
import com.roy.todo.R
import com.roy.todo.adapters.CalendarViewpagerAdapter
import com.roy.todo.data.CalendarDate
import com.roy.todo.fragments.CalendarFragment
import com.roy.todo.presenter.CalendarPresenter
import kotlinx.android.synthetic.main.activity_calendar.*
import org.jetbrains.anko.support.v4.onPageChangeListener
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

@UsingPresenter(CalendarPresenter::class)
class CalendarActivity : BaseActivity<CalendarPresenter>() {
    val today: Calendar = Calendar.getInstance()
    val currentCalendar: Calendar = Calendar.getInstance()
    lateinit var calendarPagerAdapter: CalendarViewpagerAdapter
    var currentIndex: Int = 1
    override fun initViews() {
        super.initViews()
        initViewPager()
    }

    private fun initViewPager() {
        var fragments = ArrayList<CalendarFragment>()
        val preCalendar: Calendar = Calendar.getInstance()
        preCalendar.add(Calendar.MONTH, -1)
        val nextCalendar: Calendar = Calendar.getInstance()
        nextCalendar.add(Calendar.MONTH, 1)
        fragments.add(CalendarFragment.newInstance(preCalendar))
        fragments.add(CalendarFragment.newInstance(Calendar.getInstance()))
        fragments.add(CalendarFragment.newInstance(nextCalendar))

        calendarPagerAdapter = CalendarViewpagerAdapter(supportFragmentManager, fragments)
        viewpager.adapter = calendarPagerAdapter
        viewpager.onPageChangeListener {
            onPageSelected {
                tvTitle.text = getString(R.string.current_month, fragments.get(it).currentCalendar.get(Calendar.MONTH) + 1)
            }
        }
        viewpager.currentItem = 1

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
            R.id.btnPrevious -> viewpager.currentItem = viewpager.currentItem - 1
            R.id.btnNext -> viewpager.currentItem = viewpager.currentItem + 1
        }
    }
}

