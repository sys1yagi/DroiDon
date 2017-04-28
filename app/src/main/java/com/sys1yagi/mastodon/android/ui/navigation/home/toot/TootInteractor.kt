package com.sys1yagi.mastodon.android.ui.navigation.home.toot

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import com.sys1yagi.mastodon.android.extensions.async
import com.sys1yagi.mastodon4j.api.entity.Status
import com.sys1yagi.mastodon4j.rx.RxMedia
import com.sys1yagi.mastodon4j.rx.RxStatuses
import io.reactivex.disposables.Disposables
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject


class TootInteractor
@Inject
constructor(
        val statuses: RxStatuses,
        val media: RxMedia
)
    : TootContract.Interactor {

    var out: TootContract.InteractorOutput? = null
    var disposable = Disposables.empty()

    override fun startInteraction(out: TootContract.InteractorOutput) {
        this.out = out
    }

    override fun stopInteraction(out: TootContract.InteractorOutput) {
        disposable.dispose()
        this.out = null
    }

    override fun toot(status: String, mediaIds: List<Long>?, replyToStatus: Status?) {
        disposable = async {
            statuses.postStatus(
                    status,
                    inReplyToId = replyToStatus?.id,
                    mediaIds = mediaIds
            ).await()
            out?.onSuccessToot()
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
            try {
                val attachment = media.postMedia(part).await()
                out?.onAttachmentUploaded(attachment)
            } catch(e: Throwable) {
                out?.onError(e)
            }
        }
    }
}

