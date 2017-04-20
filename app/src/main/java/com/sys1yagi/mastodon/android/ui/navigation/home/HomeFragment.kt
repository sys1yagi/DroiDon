package com.sys1yagi.mastodon.android.ui.navigation.home

import android.content.Context
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sys1yagi.fragmentcreator.annotation.FragmentCreator
import com.sys1yagi.mastodon.android.R
import com.sys1yagi.mastodon.android.databinding.FragmentHomeBinding
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

@FragmentCreator
class HomeFragment : Fragment(), HomeContract.View, TabLayout.OnTabSelectedListener {

    @Inject
    lateinit var presenter: HomeContract.Presenter

    lateinit var binding: FragmentHomeBinding

    override fun onAttach(context: Context?) {
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
        binding.viewPager.adapter = HomeViewPagerAdapter(childFragmentManager)
        binding.tabs.apply {
            addOnTabSelectedListener(this@HomeFragment)
            tabMode = TabLayout.MODE_FIXED
            tabGravity = TabLayout.GRAVITY_FILL
            getTabAt(0)?.setIcon(R.drawable.ic_chat_black_24dp)
            getTabAt(1)?.setIcon(R.drawable.ic_notifications_black_24dp)
            getTabAt(2)?.setIcon(R.drawable.ic_group_black_24dp)
            getTabAt(3)?.setIcon(R.drawable.ic_language_black_24dp)
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

    override fun onTabReselected(tab: TabLayout.Tab?) {

    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {

    }

    override fun onTabSelected(tab: TabLayout.Tab?) {

    }
}
