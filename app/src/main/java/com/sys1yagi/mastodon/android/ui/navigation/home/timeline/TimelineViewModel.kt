package com.sys1yagi.mastodon.android.ui.navigation.home.timeline

import com.sys1yagi.mastodon.android.data.model.TimelineStatus
import com.sys1yagi.mastodon4j.api.Link

class TimelineViewModel {
    var link: Link? = null
    var statuses: ArrayList<TimelineStatus> = arrayListOf()
}
