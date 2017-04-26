package com.sys1yagi.mastodon.android.ui.navigation

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.sys1yagi.mastodon.android.databinding.ListItemStatusBinding
import com.sys1yagi.mastodon.android.extensions.layoutInflator

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
        holder.binding.status = status
        status.entity.account?.let {
            holder.binding.icon.setImageURI(it.avatar)
        } ?: holder.binding.icon.setImageURI(null as String?)
    }

    override fun getItemCount() = statues.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            Holder(ListItemStatusBinding.inflate(parent.layoutInflator(), parent, false)).apply {
                binding.replay.setOnClickListener {

                }
                binding.retweet.setOnClickListener {

                }
                binding.fav.setOnClickListener {

                }
                binding.other.setOnClickListener {

                }
            }

    fun clear() {
        this.statues.clear()
    }

    fun addAll(statues: List<TimelineStatus>) {
        this.statues.addAll(0, statues)
        notifyDataSetChanged()
    }
}
