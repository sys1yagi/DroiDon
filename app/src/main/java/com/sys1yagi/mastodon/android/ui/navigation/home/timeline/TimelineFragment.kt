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

    @Inject
    lateinit var presenter: TimelineContract.Presenter

    lateinit var binding: FragmentTimelineBinding

    lateinit var subject: RecyclerViewScrolledToTheEndSubject

    val adapter: TimelineAdapter = TimelineAdapter()

    val footerAdapter: FooterAdapter = FooterAdapter()

    var recyclerViewScrollEventDisposable: Disposable = Disposables.empty()

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
        adapter.onFavClick = {
            Timber.tag("moge").d("click: ${it.isFavourited}")
            presenter.onFavClick(it)
        }
        subject = RecyclerViewScrolledToTheEndSubject(binding.recyclerView)
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
        startListenScrollEvent()
    }

    override fun onPause() {
        stopListenScrollEvent()
        presenter.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        subject.shutDown()
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

    override fun refreshTimeline() {
        if (isResumed) {
            binding.refresh.isRefreshing = true
            presenter.refresh()
        } else {
            Completable.complete().delay(1, TimeUnit.MILLISECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        binding.refresh.isRefreshing = true
                        presenter.refresh()
                    }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == TimelineContract.REQUEST_CODE_TOOT && resultCode == Activity.RESULT_OK) {
            refreshTimeline()
        }
    }

    fun startListenScrollEvent() {
        recyclerViewScrollEventDisposable.dispose()
        recyclerViewScrollEventDisposable = subject.connect().subscribe {
            stopListenScrollEvent()
            presenter.nextPage()
        }
    }

    fun stopListenScrollEvent() {
        recyclerViewScrollEventDisposable.dispose()
    }
}
