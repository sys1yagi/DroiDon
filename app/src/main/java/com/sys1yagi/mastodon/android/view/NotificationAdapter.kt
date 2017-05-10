package com.sys1yagi.mastodon.android.view

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.sys1yagi.mastodon.android.data.model.NotificationModel
import com.sys1yagi.mastodon.android.databinding.ListItemStatusBinding
import com.sys1yagi.mastodon.android.extensions.gone
import com.sys1yagi.mastodon.android.extensions.layoutInflator
import com.sys1yagi.mastodon4j.api.entity.Notification

class NotificationAdapter : RecyclerView.Adapter<NotificationAdapter.Holder>() {

    companion object {
        const val UNKNOWN = -1
        const val FOLLOW = 0
        const val FAVOURITE = 1
        const val MENTION = 2
        const val REBLOG = 3
    }

    class Holder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    var items = arrayListOf<NotificationModel>()

    var onReplayClick: OnReplayClick = {}

    var onReblogClick: OnReTweetClick = {}

    var onFavClick: OnFavClick = {}

    var onOtherClick: OnOtherClick = {}

    var onAttachmentClick: OnAttachmentClick = { _, _ -> }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return when (viewType) {
            MENTION -> {
                Holder(ListItemStatusBinding.inflate(parent.layoutInflator(), parent, false))
            }
            else -> {
                Holder(ListItemStatusBinding.inflate(parent.layoutInflator(), parent, false))
            }
        }
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val notification = items[position]
        when (holder.binding) {
            is ListItemStatusBinding -> {
                holder.binding.status = notification.status
            }
            else -> {
                holder.binding.root.gone()
            }
        }
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int) =
            when (items[position].entity.type) {
                Notification.Type.Follow.value -> {
                    FOLLOW
                }
                Notification.Type.Favourite.value -> {
                    FAVOURITE
                }
                Notification.Type.Mention.value -> {
                    MENTION
                }
                Notification.Type.Reblog.value -> {
                    REBLOG
                }
                else -> {
                    UNKNOWN
                }
            }
}
