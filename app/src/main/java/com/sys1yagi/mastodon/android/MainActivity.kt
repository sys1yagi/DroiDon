package com.sys1yagi.mastodon.android

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.sys1yagi.mastodon.android.databinding.ActivityMainBinding
import com.sys1yagi.mastodon.android.extensions.contentViewBinding

class MainActivity : AppCompatActivity() {

    val binding: ActivityMainBinding by contentViewBinding(R.layout.activity_main)

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                binding.message.setText(R.string.title_home)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                binding.message.setText(R.string.title_dashboard)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                binding.message.setText(R.string.title_notifications)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }

}
