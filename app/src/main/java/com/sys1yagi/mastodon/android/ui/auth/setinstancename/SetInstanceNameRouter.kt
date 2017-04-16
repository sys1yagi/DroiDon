package com.sys1yagi.mastodon.android.ui.auth.setinstancename

import android.app.Activity
import com.sys1yagi.mastodon.android.ui.entrypoint.EntryPointActivity
import javax.inject.Inject

class SetInstanceNameRouter
@Inject
constructor()
    : SetInstanceNameContract.Router {
    override fun openEntryPointActivity(activity: Activity) {
        activity.startActivity(EntryPointActivity.createIntent(activity))
    }
}
