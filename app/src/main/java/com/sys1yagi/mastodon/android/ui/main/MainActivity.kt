package com.sys1yagi.mastodon.android.ui.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.sys1yagi.mastodon.android.R
import com.sys1yagi.mastodon.android.databinding.ActivityMainBinding
import com.sys1yagi.mastodon.android.extensions.contentViewBinding
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    @Inject
    lateinit var presenter: MainContract.Presenter

    val binding: ActivityMainBinding by contentViewBinding(R.layout.activity_main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        binding.navigation.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem) =
            when (item.itemId) {
                R.id.navigation_home -> {
                    binding.message.setText(R.string.title_home)
                    true
                }
                R.id.navigation_dashboard -> {
                    binding.message.setText(R.string.title_dashboard)
                    true
                }
                R.id.navigation_notifications -> {
                    binding.message.setText(R.string.title_notifications)
                    true
                }
                else ->
                    false
            }
}
