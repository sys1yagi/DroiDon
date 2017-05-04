package com.sys1yagi.mastodon.android.ui.navigation.home.timeline

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sys1yagi.fragmentcreator.annotation.Args
import com.sys1yagi.fragmentcreator.annotation.FragmentCreator
import com.sys1yagi.mastodon.android.R
import com.sys1yagi.mastodon.android.databinding.FragmentTimelineBinding
import com.sys1yagi.mastodon.android.extensions.addAdapterForKotlin
import com.sys1yagi.mastodon.android.extensions.gone
import com.sys1yagi.mastodon.android.extensions.visible
import com.sys1yagi.mastodon.android.util.RecyclerViewScrolledToTheEndSubject
import com.sys1yagi.mastodon.android.util.TabLayoutEventSubject
import com.sys1yagi.mastodon.android.view.FooterAdapter
import com.sys1yagi.mastodon.android.view.TimelineAdapter
import dagger.android.support.AndroidSupportInjection
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables
import me.mvdw.recyclerviewmergeadapter.adapter.RecyclerViewMergeAdapter
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@FragmentCreator
class TimelineFragment : Fragment(), TimelineContract.View {

    @Args
    lateinit var type: StatusFetcher.Type

    @Args
    var position: Int = -1

    @Inject
    lateinit var presenter: TimelineContract.Presenter

    @Inject
    lateinit var tabLayoutEventSubject: TabLayoutEventSubject

    lateinit var binding: FragmentTimelineBinding

    lateinit var recyclerViewScrolledToTheEndSubject: RecyclerViewScrolledToTheEndSubject

    val adapter: TimelineAdapter = TimelineAdapter()

    val footerAdapter: FooterAdapter = FooterAdapter()

    var recyclerViewScrollEventDisposable: Disposable = Disposables.empty()

    var tabSelectEventDisposable: Disposable = Disposables.empty()

    override fun onAttach(context: Context?) {
        TimelineFragmentCreator.read(this)
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_timeline, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTimelineBinding.bind(view)

        val mergeAdapter = RecyclerViewMergeAdapter()
        mergeAdapter.addAdapterForKotlin(adapter)
        mergeAdapter.addAdapterForKotlin(footerAdapter)
        binding.recyclerView.also {
            it.adapter = mergeAdapter
            it.layoutManager = LinearLayoutManager(context)
        }
        binding.refresh.setOnRefreshListener {
            presenter.refresh()
        }
        adapter.onReplayClick = {
            presenter.onReplyClick(it)
        }
        adapter.onBoostClick = {
            // todo
        }
        adapter.onFavClick = {
            presenter.onFavClick(it)
        }
        adapter.onAttachmentClick = { position, attachments ->
            presenter.onAttachmentClick(position, attachments)
        }
        recyclerViewScrolledToTheEndSubject = RecyclerViewScrolledToTheEndSubject(binding.recyclerView)
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
        startListenScrollEvent()
        startListenTabSelectEvent()
    }

    override fun onPause() {
        stopListenTabSelectEvent()
        stopListenScrollEvent()
        presenter.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        recyclerViewScrolledToTheEndSubject.shutDown()
        super.onDestroy()
    }

    override fun showTimeline(viewModel: TimelineViewModel) {
        binding.progressBar.gone()
        binding.refresh.isRefreshing = false
        binding.recyclerView.visible()
        adapter.clear()
        adapter.addAll(viewModel.statuses)
        binding.recyclerView.adapter.notifyDataSetChanged()
        startListenScrollEvent()
    }

    override fun showProgress() {
        binding.progressBar.visible()
        binding.recyclerView.gone()
    }

    override fun showError(message: String) {
        binding.refresh.isRefreshing = false
    }

    override fun refreshTimelineAfterPost() {
        if (isResumed) {
            binding.refresh.isRefreshing = true
            presenter.refresh()
        } else {
            Completable.complete().delay(1, TimeUnit.MILLISECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        if (!isResumed) {
                            return@subscribe
                        }
                        binding.refresh.isRefreshing = true
                        presenter.refresh()
                    }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == TimelineContract.REQUEST_CODE_TOOT && resultCode == Activity.RESULT_OK) {
            refreshTimelineAfterPost()
        }
    }

    fun startListenScrollEvent() {
        recyclerViewScrollEventDisposable.dispose()
        recyclerViewScrollEventDisposable = recyclerViewScrolledToTheEndSubject.connect().subscribe {
            stopListenScrollEvent()
            presenter.nextPage()
        }
    }

    fun stopListenScrollEvent() {
        recyclerViewScrollEventDisposable.dispose()
    }

    fun startListenTabSelectEvent() {
        tabSelectEventDisposable.dispose()
        tabSelectEventDisposable = tabLayoutEventSubject.connect().subscribe {
            if (it.tab.position == position && it.type == TabLayoutEventSubject.EventType.RESELECTED) {
                val pos = (binding.recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                if (pos < 40) {
                    binding.recyclerView.smoothScrollToPosition(0)
                } else {
                    binding.recyclerView.scrollToPosition(0)
                }
            }
        }
    }

    fun stopListenTabSelectEvent() {
        tabSelectEventDisposable.dispose()
    }
}
