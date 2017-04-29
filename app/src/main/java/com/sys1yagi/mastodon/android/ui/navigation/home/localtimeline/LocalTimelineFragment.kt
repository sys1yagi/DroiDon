package com.sys1yagi.mastodon.android.ui.navigation.home.localtimeline

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sys1yagi.fragmentcreator.annotation.FragmentCreator
import com.sys1yagi.mastodon.android.R
import com.sys1yagi.mastodon.android.databinding.FragmentLocaltimelineBinding
import com.sys1yagi.mastodon.android.extensions.gone
import com.sys1yagi.mastodon.android.extensions.visible
import com.sys1yagi.mastodon.android.view.TimelineAdapter
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

@FragmentCreator
class LocalTimelineFragment : Fragment(), LocalTimelineContract.View {

    @Inject
    lateinit var presenter: LocalTimelineContract.Presenter

    lateinit var binding: FragmentLocaltimelineBinding

    val adapter: TimelineAdapter = TimelineAdapter()

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_localtimeline, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLocaltimelineBinding.bind(view)
        binding.recyclerView.also {
            it.adapter = adapter
            it.layoutManager = LinearLayoutManager(context)
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

    override fun showError(message: String) {

    }

    override fun showTimeline(viewModel: LocalTimelineViewModel) {
        binding.progressBar.gone()
        binding.recyclerView.visible()
        // TODO diff append
        adapter.addAll(viewModel.statuses)
    }

    override fun finish() {
        //
    }
}
