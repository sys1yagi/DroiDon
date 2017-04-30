package com.sys1yagi.mastodon.android.extensions

import android.support.v7.widget.RecyclerView
import me.mvdw.recyclerviewmergeadapter.adapter.RecyclerViewMergeAdapter

@Suppress("UNCHECKED_CAST")
fun RecyclerViewMergeAdapter.addAdapterForKotlin(adapter: RecyclerView.Adapter<out RecyclerView.ViewHolder>?) {
    adapter?.let {
        addAdapter(it as RecyclerView.Adapter<RecyclerView.ViewHolder>)
    }
}
