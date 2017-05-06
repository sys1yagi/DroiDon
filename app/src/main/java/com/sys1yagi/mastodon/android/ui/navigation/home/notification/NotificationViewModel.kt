package com.sys1yagi.mastodon.android.ui.navigation.home.notification

import com.sys1yagi.mastodon.android.data.model.Notification
import com.sys1yagi.mastodon4j.api.Link

class NotificationViewModel {
    var link: Link? = null
        get() = field
        set(value) {
            field = value
        }
    var notifications: ArrayList<Notification> = arrayListOf()
}
