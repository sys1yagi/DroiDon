package com.sys1yagi.mastodon.android.ui.navigation.home.toot

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import com.sys1yagi.mastodon.android.extensions.async
import com.sys1yagi.mastodon.android.extensions.toJob
import com.sys1yagi.mastodon.android.extensions.ui
import com.sys1yagi.mastodon4j.api.entity.Status
import com.sys1yagi.mastodon4j.api.method.Media
import com.sys1yagi.mastodon4j.api.method.Statuses
import kotlinx.coroutines.experimental.Job
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class TootInteractor
@Inject
constructor(
        val statuses: Statuses,
        val media: Media
) : TootContract.Interactor {

    var out: TootContract.InteractorOutput? = null
    var job: Job? = null

    override fun startInteraction(out: TootContract.InteractorOutput) {
        this.out = out
    }

    override fun stopInteraction(out: TootContract.InteractorOutput) {
        job?.cancel()
        this.out = null
    }

    override fun toot(status: String, mediaIds: List<Long>?, replyToStatus: ReplyTo?) {
        job = async {
            statuses.postStatus(
                    status,
                    inReplyToId = replyToStatus?.id,
                    mediaIds = mediaIds,
                    sensitive = false,
                    spoilerText = null
            ).execute()
            ui {
                out?.onSuccessToot()
            }
        }
    }

    override fun uploadAttachment(context: Context, uri: Uri) {
        fun getMetaData(context: Context, uri: Uri): Pair<String, String> {
            val cursor = context.contentResolver
                    .query(uri, null, null, null, null, null)
            try {
                return cursor?.let {
                    it.moveToFirst()
                    val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                    val name = it.getString(nameIndex)
                    val mimeTypeIndex = cursor.getColumnIndex("mime_type")
                    val mimeType = it.getString(mimeTypeIndex)

                    Pair(name, mimeType)
                } ?: throw IllegalStateException("cursor not found $uri")
            } finally {
                cursor?.close()
            }
        }
        val (name, mimeType) = getMetaData(context, uri)
        val bytes = context.contentResolver.openInputStream(uri).readBytes()

        val requestFile = RequestBody.create(MediaType.parse(mimeType), bytes)
        val part = MultipartBody.Part.createFormData("file", "DroiDon_$name", requestFile)
        async {
            val attachment = media.postMedia(part).toJob()
            ui {
                try {
                    out?.onAttachmentUploaded(attachment.await())
                } catch (e: Throwable) {
                    out?.onError(e)
                }
            }
        }
    }
}

