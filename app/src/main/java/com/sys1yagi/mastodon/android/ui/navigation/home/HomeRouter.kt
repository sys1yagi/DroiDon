package com.sys1yagi.mastodon.android.ui.navigation.home

import android.support.v4.app.Fragment
import com.sys1yagi.mastodon.android.ui.navigation.home.toot.TootActivity
import javax.inject.Inject

class HomeRouter
@Inject
constructor()
    : HomeContract.Router {
    override fun openTootActivity(fragment: Fragment, instanceName: String) {
        fragment.startActivityForResult(TootActivity.createIntent(fragment.context, instanceName), HomeContract.REQUEST_CODE_TOOT)
    }
}
