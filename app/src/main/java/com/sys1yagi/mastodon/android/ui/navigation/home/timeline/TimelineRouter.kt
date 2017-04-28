package com.sys1yagi.mastodon.android.ui.navigation.home.timeline

import android.content.Context
import com.sys1yagi.mastodon.android.ui.navigation.home.toot.TootActivity
import com.sys1yagi.mastodon4j.api.entity.Status
import javax.inject.Inject

class TimelineRouter
@Inject
constructor()
    : TimelineContract.Router {
    override fun openTootActivity(context: Context, instanceName: String, replyToStatus: Status?) {
        context.startActivity(TootActivity.createIntent(context, instanceName, replyToStatus))
    }
}
