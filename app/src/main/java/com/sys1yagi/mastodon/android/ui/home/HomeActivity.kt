package com.sys1yagi.mastodon.android.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.sys1yagi.mastodon.android.R
import com.sys1yagi.mastodon.android.databinding.ActivityHomeBinding
import com.sys1yagi.mastodon.android.extensions.contentViewBinding
import com.sys1yagi.mastodon.android.ui.home.localtimeline.LocalTimelineFragmentCreator
import dagger.android.AndroidInjection
import javax.inject.Inject
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasDispatchingSupportFragmentInjector


class HomeActivity : AppCompatActivity(), HomeContract.View, BottomNavigationView.OnNavigationItemSelectedListener, HasDispatchingSupportFragmentInjector {

    companion object {
        const val HOME_TAG = "home"
        const val TRIP_TAG = "trip"
        const val SETTINGS_TAG = "settings"
        fun createIntent(context: Context) = Intent(context, HomeActivity::class.java)
    }

    @Inject
    lateinit var presenter: HomeContract.Presenter

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    val binding: ActivityHomeBinding by contentViewBinding(R.layout.activity_home)

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = getString(R.string.app_name)
        }
        binding.navigation.setOnNavigationItemSelectedListener(this)
        showHome()
    }

    override fun onNavigationItemSelected(item: MenuItem) = presenter.onNavigationItemSelected(item)

    override fun showHome() {
        val fragment = supportFragmentManager.findFragmentByTag(HOME_TAG) ?: LocalTimelineFragmentCreator.newBuilder().build()
        switchFragment(fragment, HOME_TAG)
        binding.navigation.menu.findItem(R.id.navigation_home).isChecked = true
    }

    override fun showTrip() {

    }

    override fun showSettings() {

    }

    fun switchFragment(fragment: Fragment, tag: String): Boolean {
        if (fragment.isAdded) {
            return false
        }

        val ft = supportFragmentManager.beginTransaction()
        supportFragmentManager.findFragmentById(binding.content.id)?.let { ft.detach(it) }

        if (fragment.isDetached) {
            ft.attach(fragment)
        } else {
            ft.add(binding.content.id, fragment, tag)
        }
        ft.commit()
        supportFragmentManager.executePendingTransactions()

        return true
    }

    override fun supportFragmentInjector(): DispatchingAndroidInjector<Fragment> {
        return fragmentInjector
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun onPause() {
        presenter.onPause()
        super.onPause()
    }
}
