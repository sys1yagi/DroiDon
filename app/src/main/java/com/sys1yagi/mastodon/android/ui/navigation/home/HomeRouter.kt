package com.sys1yagi.mastodon.android.ui.navigation.home

import android.content.Context
import com.sys1yagi.mastodon.android.ui.navigation.home.toot.TootActivity
import javax.inject.Inject

class HomeRouter
@Inject
constructor()
    : HomeContract.Router {
    override fun openTootActivity(context: Context, instanceName: String) {
        context.startActivity(TootActivity.createIntent(context, instanceName))
    }
}
