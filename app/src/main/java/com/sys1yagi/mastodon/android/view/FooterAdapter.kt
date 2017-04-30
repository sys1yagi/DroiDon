package com.sys1yagi.mastodon.android.view

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.sys1yagi.mastodon.android.databinding.ListItemFooterBinding
import com.sys1yagi.mastodon.android.extensions.gone
import com.sys1yagi.mastodon.android.extensions.layoutInflator
import com.sys1yagi.mastodon.android.extensions.visible

typealias OnRetryClick = () -> Unit

class FooterAdapter : RecyclerView.Adapter<FooterAdapter.Holder>() {

    enum class State {
        PROGRESS,
        FAILED,
        COMPLETED
    }

    var state: State = State.PROGRESS
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onRetryClick: OnRetryClick = {}

    class Holder(val binding: ListItemFooterBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: FooterAdapter.Holder, position: Int) {
        when (state) {
            State.PROGRESS -> {
                holder.binding.apply {
                    root.visible()
                    errorText.gone()
                    progressBar.visible()
                }
            }
            State.FAILED -> {
                holder.binding.apply {
                    root.visible()
                    errorText.visible()
                    errorText.setOnClickListener {
                        onRetryClick()
                    }
                    progressBar.gone()
                }
            }
            State.COMPLETED -> {
                holder.binding.root.gone()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = Holder(ListItemFooterBinding.inflate(parent.layoutInflator(), parent, false))

    override fun getItemCount() = 1
}
