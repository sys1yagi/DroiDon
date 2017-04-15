package com.sys1yagi.mastodon.android.ui.main

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.sys1yagi.mastodon.android.databinding.ListItemStatusBinding
import com.sys1yagi.mastodon.android.extensions.layoutInflator

class TimelineAdapter : RecyclerView.Adapter<TimelineAdapter.Holder>() {

    class Holder(val binding: ListItemStatusBinding) : RecyclerView.ViewHolder(binding.root)

    private val statues = arrayListOf<TimelineStatus>()

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val status = statues[position]
        holder.binding.status = status
        status.entity.account?.let {
            holder.binding.icon.setImageURI(it.avatar)
        } ?: holder.binding.icon.setImageURI(null as String?)
    }

    override fun getItemCount() = statues.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            Holder(ListItemStatusBinding.inflate(parent.layoutInflator(), parent, false))

    fun addAll(statues: List<TimelineStatus>) {
        this.statues.addAll(statues)
        notifyDataSetChanged()
    }
}
