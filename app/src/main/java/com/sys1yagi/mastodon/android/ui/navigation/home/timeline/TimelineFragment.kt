package com.sys1yagi.mastodon.android.ui.navigation.home.timeline

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sys1yagi.fragmentcreator.annotation.FragmentCreator
import com.sys1yagi.mastodon.android.R
import com.sys1yagi.mastodon.android.databinding.FragmentTimelineBinding
import com.sys1yagi.mastodon.android.extensions.gone
import com.sys1yagi.mastodon.android.extensions.visible
import com.sys1yagi.mastodon.android.ui.navigation.TimelineAdapter
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

@FragmentCreator
class TimelineFragment : Fragment(), TimelineContract.View {

    @Inject
    lateinit var presenter: TimelineContract.Presenter

    lateinit var binding: FragmentTimelineBinding

    val adapter: TimelineAdapter = TimelineAdapter()

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_timeline, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTimelineBinding.bind(view)
        binding.recyclerView.also {
            it.adapter = adapter
            it.layoutManager = LinearLayoutManager(context)
        }
        binding.refresh.setOnRefreshListener {
            presenter.refresh()
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun onPause() {
        super.onPause()
        presenter.onPause()
    }

    override fun showTimeline(viewModel: TimelineViewModel) {
        binding.progressBar.gone()
        binding.refresh.isRefreshing = false
        binding.recyclerView.visible()
        adapter.clear()
        adapter.addAll(viewModel.statuses)
    }

    override fun showProgress() {
        binding.progressBar.visible()
        binding.recyclerView.gone()
    }

    override fun showError(message: String) {
        binding.refresh.isRefreshing = false
    }
}
