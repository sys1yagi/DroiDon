package com.sys1yagi.mastodon.android.ui.navigation.home.timeline

import android.support.v4.app.Fragment
import com.sys1yagi.mastodon.android.ui.navigation.home.toot.TootActivity
import com.sys1yagi.mastodon4j.api.entity.Status
import javax.inject.Inject

class TimelineRouter
@Inject
constructor()
    : TimelineContract.Router {
    override fun openTootActivity(fragment: Fragment, instanceName: String, replyToStatus: Status?) {
        fragment.startActivityForResult(TootActivity.createIntent(fragment.context, instanceName, replyToStatus), TimelineContract.REQUEST_CODE_TOOT)
    }
}
