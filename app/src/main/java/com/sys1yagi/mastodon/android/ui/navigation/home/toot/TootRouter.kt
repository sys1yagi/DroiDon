package com.sys1yagi.mastodon.android.ui.navigation.home.toot

import android.app.Activity
import android.content.Intent
import javax.inject.Inject

class TootRouter
@Inject
constructor()
    : TootContract.Router {

    override fun chooseAttachment(activity: Activity) {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.type = "image/*"
        activity.startActivityForResult(intent, TootContract.REQUEST_ID_CHOOSE_ATTACHMENT)
    }
}
