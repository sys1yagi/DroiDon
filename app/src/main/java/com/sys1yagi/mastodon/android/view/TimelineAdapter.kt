package com.sys1yagi.mastodon.android.view

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.sys1yagi.mastodon.android.databinding.ListItemStatusBinding
import com.sys1yagi.mastodon.android.extensions.layoutInflator
import com.sys1yagi.mastodon.android.data.model.TimelineStatus

typealias OnReplayClick = (TimelineStatus) -> Unit
typealias OnReTweetClick = (TimelineStatus) -> Unit
typealias OnFavClick = (TimelineStatus) -> Unit
typealias OnOtherClick = (TimelineStatus) -> Unit

class TimelineAdapter : RecyclerView.Adapter<TimelineAdapter.Holder>() {

    class Holder(val binding: ListItemStatusBinding) : RecyclerView.ViewHolder(binding.root)

    private val statues = arrayListOf<TimelineStatus>()

    var onReplayClick: OnReplayClick = {}

    var onReTweetClick: OnReTweetClick = {}

    var onFavClick: OnFavClick = {}

    var onOtherClick: OnOtherClick = {}

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val status = statues[position]

        status.entity.account?.let {
            holder.binding.icon.setImageURI(it.avatar)
        } ?: holder.binding.icon.setImageURI(null as String?)

        holder.apply {
            binding.status = status
            binding.replay.setOnClickListener {
                onReplayClick(status)
            }
            binding.retweet.setOnClickListener {
                onReTweetClick(status)
            }
            binding.fav.setOnClickListener {
                onFavClick(status)
            }
            binding.other.setOnClickListener {
                onOtherClick(status)
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