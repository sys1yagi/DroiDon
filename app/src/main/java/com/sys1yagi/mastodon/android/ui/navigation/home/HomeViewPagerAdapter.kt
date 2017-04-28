package com.sys1yagi.mastodon.android.ui.navigation.home

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.sys1yagi.mastodon.android.ui.navigation.home.federatedtimeline.FederatedTimelineFragmentCreator
import com.sys1yagi.mastodon.android.ui.navigation.home.localtimeline.LocalTimelineFragmentCreator
import com.sys1yagi.mastodon.android.ui.navigation.home.notification.NotificationFragmentCreator
import com.sys1yagi.mastodon.android.ui.navigation.home.timeline.TimelineFragmentCreator

class HomeViewPagerAdapter(
        fragmentManager: FragmentManager
) : FragmentStatePagerAdapter(fragmentManager) {

    val timelineFragment by lazy { TimelineFragmentCreator.newBuilder().build() }
    val notificationFragment by lazy { NotificationFragmentCreator.newBuilder().build() }
    val localTimelineFragment by lazy { LocalTimelineFragmentCreator.newBuilder().build() }
    val federatedTimelineFragment by lazy { FederatedTimelineFragmentCreator.newBuilder().build() }

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
