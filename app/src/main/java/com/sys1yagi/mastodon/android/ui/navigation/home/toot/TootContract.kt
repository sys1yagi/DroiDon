package com.sys1yagi.mastodon.android.ui.navigation.home.toot

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import com.sys1yagi.mastodon4j.api.entity.Attachment

interface TootContract {

    companion object {
        const val REQUEST_ID_CHOOSE_ATTACHMENT = 0x235
    }

    interface View {
        fun showProgress()
        fun showError(message: String)
        fun showAttachment(viewModel: TootViewModel)
        fun finish()
    }

    interface Presenter {
        fun onResume() // base
        fun onPause()  // base
        fun toot(status: String)
        fun onClickChooseAttachment()
        fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    }

    interface Interactor {
        fun startInteraction(out: InteractorOutput) // base
        fun stopInteraction(out: InteractorOutput) // base
        fun toot(status: String, mediaIds: List<Long>? = null)
        fun uploadAttachment(context: Context, uri: Uri)
    }

    interface InteractorOutput {
        fun onError(t: Throwable)
        fun onSuccessToot()
        fun onAttachmentUploaded(attachment: Attachment)
    }

    interface Router {
        fun chooseAttachment(activity: Activity)
    }
}
