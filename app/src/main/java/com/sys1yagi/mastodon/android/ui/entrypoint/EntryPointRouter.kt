package com.sys1yagi.mastodon.android.ui.entrypoint

import android.app.Activity
import com.sys1yagi.mastodon.android.R
import com.sys1yagi.mastodon.android.ui.auth.setinstancename.SetInstanceNameActivity
import javax.inject.Inject

class EntryPointRouter
@Inject
constructor()
    : EntryPointContract.Router {
    override fun openSetInstanceNameActivity(activity: Activity) {
        activity.startActivity(SetInstanceNameActivity.createIntent(activity))
        activity.overridePendingTransition(
                R.anim.slide_in_bottom,
                R.anim.slide_out_top
        )
    }

    override fun openHomeActivity(activity: Activity) {
        // TODO
    }
}
