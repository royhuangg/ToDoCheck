package com.roy.todo.adapters

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.roy.todo.fragments.CalendarFragment

/**
 * Created by Roy on 2017/7/27.
 */
class CalendarViewpagerAdapter(fm: FragmentManager, var fragments: ArrayList<CalendarFragment>) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): android.support.v4.app.Fragment? {
        return fragments[position]
    }


    override fun getCount(): Int {
        return fragments.size
    }

}