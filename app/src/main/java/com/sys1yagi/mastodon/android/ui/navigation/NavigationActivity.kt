package com.sys1yagi.mastodon.android.ui.navigation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.sys1yagi.mastodon.android.R
import com.sys1yagi.mastodon.android.databinding.ActivityNavigationBinding
import com.sys1yagi.mastodon.android.extensions.contentViewBinding
import com.sys1yagi.mastodon.android.extensions.getRequired
import com.sys1yagi.mastodon.android.ui.navigation.home.HomeFragmentCreator
import com.sys1yagi.mastodon.android.ui.navigation.settings.SettingsFragmentCreator
import com.sys1yagi.mastodon.android.ui.navigation.trip.TripFragmentCreator
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasDispatchingSupportFragmentInjector
import javax.inject.Inject


class NavigationActivity : AppCompatActivity(), NavigationContract.View, BottomNavigationView.OnNavigationItemSelectedListener, HasDispatchingSupportFragmentInjector {

    companion object {
        const val HOME_TAG = "home"
        const val TRIP_TAG = "trip"
        const val SETTINGS_TAG = "settings"

        const val ARGS_INSTANCE_NAME = "instance_name"

        fun createIntent(context: Context, instanceName: String) = Intent(context, NavigationActivity::class.java).apply {
            putExtra(ARGS_INSTANCE_NAME, instanceName)
        }
    }

    @Inject
    lateinit var presenter: NavigationContract.Presenter

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    val primaryInstanceName by lazy<String> { intent.getRequired(ARGS_INSTANCE_NAME) }

    val binding: ActivityNavigationBinding by contentViewBinding(R.layout.activity_navigation)

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
        val fragment = supportFragmentManager.findFragmentByTag(HOME_TAG) ?: HomeFragmentCreator.newBuilder(primaryInstanceName).build()
        switchFragment(fragment, HOME_TAG)
        binding.navigation.menu.findItem(R.id.navigation_home).isChecked = true
    }

    override fun showTrip() {
        val fragment = supportFragmentManager.findFragmentByTag(TRIP_TAG) ?: TripFragmentCreator.newBuilder().build()
        switchFragment(fragment, TRIP_TAG)
        binding.navigation.menu.findItem(R.id.navigation_home).isChecked = true
    }

    override fun showSettings() {
        val fragment = supportFragmentManager.findFragmentByTag(SETTINGS_TAG) ?: SettingsFragmentCreator.newBuilder().build()
        switchFragment(fragment, SETTINGS_TAG)
        binding.navigation.menu.findItem(R.id.navigation_home).isChecked = true
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }
}
