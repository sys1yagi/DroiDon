package com.sys1yagi.mastodon.android.ui.navigation.home

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sys1yagi.fragmentcreator.annotation.Args
import com.sys1yagi.fragmentcreator.annotation.FragmentCreator
import com.sys1yagi.mastodon.android.R
import com.sys1yagi.mastodon.android.databinding.FragmentHomeBinding
import com.sys1yagi.mastodon.android.ui.navigation.home.timeline.TimelineContract
import com.sys1yagi.mastodon.android.util.TabLayoutEventSubject
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

@FragmentCreator
class HomeFragment : Fragment(), HomeContract.View {

    @Args
    var instanceName: String = ""

    @Inject
    lateinit var presenter: HomeContract.Presenter

    @Inject
    lateinit var tabLayoutEventSubject: TabLayoutEventSubject

    lateinit var binding: FragmentHomeBinding

    lateinit var adapter: HomeViewPagerAdapter

    override fun onAttach(context: Context?) {
        HomeFragmentCreator.read(this)
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        binding.tabs.setupWithViewPager(binding.viewPager)
        adapter = HomeViewPagerAdapter(childFragmentManager)
        binding.viewPager.adapter = adapter
        binding.tabs.apply {
            tabMode = TabLayout.MODE_FIXED
            tabGravity = TabLayout.GRAVITY_FILL
            getTabAt(0)?.setIcon(R.drawable.ic_timeline)
            getTabAt(1)?.setIcon(R.drawable.ic_notifications)
            getTabAt(2)?.setIcon(R.drawable.ic_local)
            getTabAt(3)?.setIcon(R.drawable.ic_federated)
        }
        binding.fab.setOnClickListener {
            presenter.onFabClick()
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
        tabLayoutEventSubject.start(binding.tabs)
    }

    override fun onPause() {
        super.onPause()
        presenter.onPause()
        tabLayoutEventSubject.shutdown(binding.tabs)
    }

    override fun showError(message: String) {

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == HomeContract.REQUEST_CODE_TOOT && resultCode == Activity.RESULT_OK) {
            adapter.forEach {
                if (it is TimelineContract.View) {
                    it.refreshTimelineAfterPost()
                    return@forEach
                }
            }
        }
    }
}
