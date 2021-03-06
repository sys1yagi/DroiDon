package com.sys1yagi.mastodon.android.view

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.LinearLayout
import com.facebook.drawee.generic.RoundingParams
import com.facebook.drawee.view.SimpleDraweeView
import com.sys1yagi.mastodon.android.R
import com.sys1yagi.mastodon.android.data.model.TimelineStatus
import com.sys1yagi.mastodon.android.databinding.ListItemStatusBinding
import com.sys1yagi.mastodon.android.extensions.getDimensionPixelSize
import com.sys1yagi.mastodon.android.extensions.gone
import com.sys1yagi.mastodon.android.extensions.layoutInflator
import com.sys1yagi.mastodon.android.extensions.visible
import com.sys1yagi.mastodon4j.api.entity.Attachment
import io.reactivex.Observable


typealias OnReplayClick = (TimelineStatus) -> Unit
typealias OnReTweetClick = (TimelineStatus) -> Unit
typealias OnFavClick = (TimelineStatus) -> Unit
typealias OnOtherClick = (TimelineStatus) -> Unit
typealias OnAttachmentClick = (Int, List<Attachment>) -> Unit

class TimelineAdapter : RecyclerView.Adapter<TimelineAdapter.Holder>() {

    class Holder(val binding: ListItemStatusBinding) : RecyclerView.ViewHolder(binding.root)

    private val statues = arrayListOf<TimelineStatus>()

    var onReplayClick: OnReplayClick = {}

    var onReblogClick: OnReTweetClick = {}

    var onFavClick: OnFavClick = {}

    var onOtherClick: OnOtherClick = {}

    var onAttachmentClick: OnAttachmentClick = { _, _ -> }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val timelineStatus = statues[position]
        val status = timelineStatus.reblog?.let { it } ?: timelineStatus
        val rebloggedBy = timelineStatus.reblog?.let { timelineStatus.entity.account }
        status.entity.account?.let {
            holder.binding.icon.setImageURI(it.avatar)
        } ?: holder.binding.icon.setImageURI(null as String?)

        showMedia(holder.binding.mediaContainer, status)

        holder.apply {
            binding.status = status
            binding.rebloggedBy = rebloggedBy
            binding.replay.setOnClickListener {
                onReplayClick(status)
            }
            binding.retweet.setOnClickListener {
                onReblogClick(status)
            }
            binding.fav.setOnClickListener {
                onFavClick(status)
            }
            binding.other.setOnClickListener {
                onOtherClick(status)
            }
        }
    }

    fun showMedia(mediaContainer: LinearLayout, status: TimelineStatus) {
        val context = mediaContainer.context
        mediaContainer.removeAllViews()
        if (status.entity.mediaAttachments.isEmpty()) {
            mediaContainer.gone()
        } else {
            mediaContainer.visible()
            val attachments = status.entity.mediaAttachments
            attachments.forEachIndexed { i, attachment ->
                val image = SimpleDraweeView(context)
                val roundingParams = RoundingParams.fromCornersRadius(8f)
                image.hierarchy.roundingParams = roundingParams
                image.setImageURI(attachment.previewUrl)
                val params = LinearLayout.LayoutParams(0, context.getDimensionPixelSize(R.dimen.toot_media_preview_height))
                params.weight = 1f
                params.leftMargin = 2
                mediaContainer.addView(image, params)
                image.setOnClickListener {
                    onAttachmentClick(i, attachments)
                }
            }
        }
    }

    override fun getItemCount() = statues.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            Holder(ListItemStatusBinding.inflate(parent.layoutInflator(), parent, false))

    fun clear() {
        this.statues.clear()
    }

    fun addAll(statues: List<TimelineStatus>) {
        this.statues.addAll(0, statues)
        notifyDataSetChanged()
    }
}
