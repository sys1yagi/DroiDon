package com.sys1yagi.mastodon.android.util

import android.support.design.widget.TabLayout
import io.reactivex.subjects.PublishSubject

class TabLayoutEventSubject() : TabLayout.OnTabSelectedListener {

    enum class EventType {
        RESELECTED,
        SELECTED,
        UNSELECTED
    }

    class Event(val tab: TabLayout.Tab, val type: EventType)

    private val subject = PublishSubject.create<Event>()

    fun connect() = subject

    fun start(tabLayout: TabLayout) {
        tabLayout.addOnTabSelectedListener(this)
    }

    fun shutdown(tabLayout: TabLayout) {
        tabLayout.removeOnTabSelectedListener(this)
    }

    override fun onTabReselected(tab: TabLayout.Tab) {
        subject.onNext(Event(tab, EventType.RESELECTED))
    }

    override fun onTabUnselected(tab: TabLayout.Tab) {
        subject.onNext(Event(tab, EventType.UNSELECTED))
    }

    override fun onTabSelected(tab: TabLayout.Tab) {
        subject.onNext(Event(tab, EventType.SELECTED))
    }
}
