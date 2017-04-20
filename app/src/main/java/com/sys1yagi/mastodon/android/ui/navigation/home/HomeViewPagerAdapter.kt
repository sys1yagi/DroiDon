package com.sys1yagi.mastodon.android.ui.navigation.home

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.sys1yagi.mastodon.android.ui.navigation.home.localtimeline.LocalTimelineFragmentCreator
import com.sys1yagi.mastodon.android.ui.navigation.home.timeline.TimelineFragmentCreator

class HomeViewPagerAdapter(
        fragmentManager: FragmentManager
) : FragmentStatePagerAdapter(fragmentManager) {
    
    override fun getItem(position: Int) = when (position) {
        0 -> {
            TimelineFragmentCreator.newBuilder().build()
        }
        1 -> {
            LocalTimelineFragmentCreator.newBuilder().build()
        }
        2 -> {
            LocalTimelineFragmentCreator.newBuilder().build()
        }
        else -> {
            LocalTimelineFragmentCreator.newBuilder().build()
        }
    }

    override fun getCount() = 4
}
