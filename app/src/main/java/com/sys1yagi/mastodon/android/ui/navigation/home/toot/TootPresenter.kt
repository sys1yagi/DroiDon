package com.sys1yagi.mastodon.android.ui.navigation.home.toot

import android.app.Activity
import android.content.Intent
import com.sys1yagi.mastodon4j.api.entity.Attachment
import com.sys1yagi.mastodon4j.api.entity.Status
import javax.inject.Inject


class TootPresenter
@Inject constructor(
        val activity: Activity,
        val view: TootContract.View,
        val interactor: TootContract.Interactor,
        val router: TootContract.Router
) : TootContract.Presenter, TootContract.InteractorOutput {

    val viewModel = TootViewModel()

    override fun onResume() {
        interactor.startInteraction(this)
    }

    override fun onPause() {
        interactor.stopInteraction(this)
    }

    override fun toot(status: String, replyToStatus: Status?) {
        view.showProgress()
        interactor.toot(status, viewModel.media.map { it.id }.takeIf { it.isNotEmpty() }, replyToStatus)
    }

    override fun onSuccessToot() {
        activity.setResult(Activity.RESULT_OK)
        activity.finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            TootContract.REQUEST_ID_CHOOSE_ATTACHMENT -> {
                if (resultCode != Activity.RESULT_OK) {
                    return
                }
                data?.let {
                    view.showProgress()
                    interactor.uploadAttachment(activity, it.data)
                }
            }
        }
    }

    override fun onAttachmentUploaded(attachment: Attachment) {
        viewModel.media.add(attachment)
        view.showAttachment(viewModel)
    }

    override fun onError(t: Throwable) {
        // TODO
        t.printStackTrace()
        view.showError(t.message ?: "error")
    }

    override fun onClickChooseAttachment() {
        router.chooseAttachment(activity)
    }
}
