package com.sys1yagi.mastodon.android.ui.navigation.home

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.sys1yagi.mastodon.android.ui.navigation.home.notification.NotificationFragmentCreator
import com.sys1yagi.mastodon.android.ui.navigation.home.timeline.StatusFetcher
import com.sys1yagi.mastodon.android.ui.navigation.home.timeline.TimelineFragmentCreator

class HomeViewPagerAdapter(
        fragmentManager: FragmentManager
) : FragmentStatePagerAdapter(fragmentManager) {

    private val timelineFragment by lazy { TimelineFragmentCreator.newBuilder(StatusFetcher.Type.Home, 0).build() }
    private val notificationFragment by lazy { NotificationFragmentCreator.newBuilder().build() }
    private val localTimelineFragment by lazy { TimelineFragmentCreator.newBuilder(StatusFetcher.Type.LocalPublic, 2).build() }
    private val federatedTimelineFragment by lazy { TimelineFragmentCreator.newBuilder(StatusFetcher.Type.FederatedPublic, 3).build() }

    override fun getItem(position: Int) = when (position) {
        0 -> timelineFragment
        1 -> notificationFragment
        2 -> localTimelineFragment
        else -> federatedTimelineFragment
    }

    fun forEach(f: (Fragment) -> Unit) {
        (0..count).forEach {
            f(getItem(it))
        }
    }

    override fun getCount() = 4
}
